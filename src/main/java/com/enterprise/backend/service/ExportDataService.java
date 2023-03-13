package com.enterprise.backend.service;

import com.enterprise.backend.DTO.Idea.IdeaExportRequest;
import com.enterprise.backend.model.Client;
import com.enterprise.backend.model.Idea;
import com.enterprise.backend.repo.ClientRepo;
import com.enterprise.backend.repo.IdeaRepo;
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

    //IN PROGRESS//
    public void downloadtoCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());

        String filename = "client_" + timeStamp + ".csv";

        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename = " + filename;

        response.setHeader(headerkey, headervalue);

        List<Idea> ideaList = ideaRepo.findAll();

        ICsvBeanWriter csvBeanWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

        String[] csvHeader = {"Id", "Idea Name", "Idea content", "Idea date", "Modify date", "attached_path", "topic", "client", "reactions", "comments", "idea_cate", "isAnonymous"};
        String[] mapping = {"id", "name", "body", "date", "modify_date", "attached_path", "topic", "client", "reactions", "comments", "idea_cate", "isAnonymous"};
        csvBeanWriter.writeHeader(csvHeader);

        for (Idea ideas : ideaList) {


            IdeaExportRequest ideaExportRequest = new IdeaExportRequest();
            ideaExportRequest.setId(ideas.getId());
            ideaExportRequest.setName(ideas.getName());
            ideaExportRequest.setDate(ideas.getDate());
            System.out.println(ideaExportRequest.toString());

            try {
                csvBeanWriter.write(ideas, mapping);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        csvBeanWriter.close();

    }
}
