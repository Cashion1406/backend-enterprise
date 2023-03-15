package com.enterprise.backend.service;

import com.enterprise.backend.DTO.Idea.IdeaExportRequest;
import com.enterprise.backend.model.Client;
import com.enterprise.backend.model.Idea;
import com.enterprise.backend.repo.ClientRepo;
import com.enterprise.backend.repo.IdeaRepo;
import com.enterprise.backend.repo.TopicRepo;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

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

        String filename = "Idea_" + topicRepo.getTopicname(id) + "_" + timeStamp + ".csv";

        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename = " + filename;

        response.setHeader(headerkey, headervalue);

        List<Idea> ideaList = ideaRepo.getIdeaWithTopic(id);

        ICsvBeanWriter csvBeanWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

        String[] csvHeader = {"Idea ID", "Idea Name", "Idea content", "Created on", "Modify date", "attached_path", "Topic", "Category", "Client Name", "Client Id"};
        String[] mapping = {"id", "name", "body", "date", "modify_date", "attached_path", "topic_name", "category_name", "client_name", "client_id"};
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

            try {
                csvBeanWriter.write(ideaExportRequest, mapping);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        csvBeanWriter.close();

    }
}
