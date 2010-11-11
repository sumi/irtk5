package com.irelaxa.irtk.server;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
public class UploadFileServlet extends HttpServlet { 
        private BlobstoreService blobstoreService = 
BlobstoreServiceFactory.getBlobstoreService(); 
        public void doPost(HttpServletRequest req, HttpServletResponse res) 
                throws ServletException, IOException { 
            Map<String, BlobKey> blobs = 
blobstoreService.getUploadedBlobs(req); 
            BlobKey blobKey = blobs.get("pdfFile"); 
            Entity uploadedImage = new Entity("UploadedImage");
            uploadedImage.setProperty("blobKey", blobKey);
           // uploadedImage.setProperty(UploadedImage.CREATED_AT, new Date());
            DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
            datastore.put(uploadedImage);
            
        } 
} 