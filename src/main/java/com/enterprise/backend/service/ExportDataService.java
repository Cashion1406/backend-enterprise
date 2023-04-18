package com.enterprise.backend.service;

import com.enterprise.backend.DTO.Idea.IdeaExportRequest;
import com.enterprise.backend.model.Idea;
import com.enterprise.backend.repo.ClientRepo;
import com.enterprise.backend.repo.IdeaRepo;
import com.enterprise.backend.repo.TopicRepo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ExportDataService {

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private IdeaRepo ideaRepo;

    @Autowired
    private TopicRepo topicRepo;


    //IN PROGRESS//
    public void downloadtoCSV(HttpServletResponse response, Long id) throws IOException {

        response.setContentType("text/csv");

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());


        String filename = "Idea_" + topicRepo.getTopicname( id) + "_" + timeStamp + ".csv";

        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename = " + filename;

        response.setHeader(headerkey, headervalue);

        List<Idea> ideaList = ideaRepo.getIdeaWithTopic(id);

        ICsvBeanWriter csvBeanWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

        String[] csvHeader = {"Idea ID", "Idea Name", "Idea content", "Created on", "Modify date", "attached_path", "Topic", "Category", "Client Name", "Client Id","Client Email"};
        String[] mapping = {"id", "name", "body", "date", "modify_date", "attached_path", "topic_name", "category_name", "client_name", "client_id","client_email"};
        csvBeanWriter.writeHeader(csvHeader);
        for (Idea ideas : ideaList) {

            IdeaExportRequest ideaExportRequest = new IdeaExportRequest();

            ideaExportRequest.setId(ideas.getId());
            ideaExportRequest.setName(ideas.getName());
            ideaExportRequest.setDate(ideas.getDate());
            ideaExportRequest.setBody(ideas.getBody());
            ideaExportRequest.setAttached_path(ideas.getAttached_path());
            ideaExportRequest.setClient_name((clientRepo.getClientName(ideas.getClient().getId())));
            ideaExportRequest.setCategory_name(ideaRepo.getCateName(ideas.getId()).toString());
            ideaExportRequest.setModify_date(ideas.getModify_date());
            ideaExportRequest.setTopic_name(ideaRepo.getTopicNameEachIdea(ideas.getId()));
            ideaExportRequest.setClient_id(ideas.getClient().getId());
            ideaExportRequest.setClient_email(ideas.getClient().getEmail());

            try {
                csvBeanWriter.write(ideaExportRequest, mapping);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        csvBeanWriter.close();

    }

    private byte[] downloadFile(String url) throws IOException {
        URL fileUrl = new URL(url);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        InputStream inputStream = fileUrl.openStream();

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        return outputStream.toByteArray();
    }

    private String getFileNameFromUrl(String url) {
        int index = url.lastIndexOf("/") + 1;

        //extract file name from first string url after the "/" character
        String first_string = url.substring(index);

        int index2 = first_string.lastIndexOf("?");

        //remove every character after character "?" to get the file name with proper file extension
        String output_String = first_string.substring(0, index2);

        int index3 = output_String.indexOf("F");
        //if the string contain folder name on firestorage, remove the boiler character
        if (index3 != -1) {
            return output_String.substring(index3 + 1);
        } else {
            return output_String;
        }

    }

    public ResponseEntity<Resource> downloadToZip(Long id) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);


        List<Idea> ideaList = ideaRepo.getIdeaWithTopic(id);
        for (Idea idea : ideaList) {
            byte[] fileBytes = downloadFile(idea.getAttached_path());
            ZipEntry zipEntry = new ZipEntry(getFileNameFromUrl(idea.getAttached_path()));
            zipOutputStream.putNextEntry(zipEntry);
            zipOutputStream.write(fileBytes);
            zipOutputStream.closeEntry();
        }
        zipOutputStream.close();
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());
        String filename = "Documents for Topic " + topicRepo.getTopicname(id) + ".zip";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + filename)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .body(resource);

    }


}
