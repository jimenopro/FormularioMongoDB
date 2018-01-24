/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bson.Document;

/**
 *
 * @author josej
 */
public class ServletMongoDBCorregir extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://jimenopro:admin@cluster0-qwfqm.mongodb.net/test");
        //Connect

        MongoClient mongoClient = new MongoClient(uri);
        //Create Database
        MongoDatabase database = mongoClient.getDatabase("prueba9");
        //Create collection
        MongoCollection<Document> collection = database.getCollection("notas9");

        Document a = new Document("notas", collection.find());

        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            out.println(a.toJson());
            mongoClient.close();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("dni");
        String tipoExamen = request.getParameter("tipoExamen");
        String select = request.getParameter("select");
        String radio = request.getParameter("radio");
        String text = request.getParameter("text");
        String checkbox = request.getParameter("checkbox");
        String multiple = request.getParameter("multiple");
        String r1 = request.getParameter("r1");
        String r2 = request.getParameter("r2");
        String r3 = request.getParameter("r3");
        String r4 = request.getParameter("r4");
        String r5 = request.getParameter("r5");
        int nota = 0;

        if (select == null ? r1 == null : select.equals(r1)) {
            nota += 2;
        } else {
            if (nota > 0) {
                nota -= 1;
            }
        }
        if (radio == null ? r2 == null : radio.equals(r2)) {
            nota += 2;
        } else {
            if (nota > 0) {
                nota -= 1;
            }
        }
        if (text == null ? r3 == null : text.equals(r3)) {
            nota += 2;
        } else {
            if (nota > 0) {
                nota -= 1;
            }
        }
        if (checkbox == null ? r4 == null : checkbox.equals(r4)) {
            nota += 2;
        } else {
            if (nota > 0) {
                nota -= 1;
            }
        }
        if (multiple == null ? r5 == null : multiple.equals(r5)) {
            nota += 2;
        } else {
            if (nota > 0) {
                nota -= 1;
            }
        }
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://jimenopro:admin@cluster0-qwfqm.mongodb.net/test");
        //Connect

        MongoClient mongoClient = new MongoClient(uri);
        //Create Database
        MongoDatabase database = mongoClient.getDatabase("prueba9");
        //Create collection
        MongoCollection<Document> collection = database.getCollection("notas9");

        Document nota1 = new Document("nota", "nota")
                .append("dni", dni)
                .append("tipoExamen", tipoExamen)
                .append("nota", nota);

        MongoCursor<Document> cursor = collection.find(and(eq("dni", dni), eq("tipoExamen", tipoExamen))).iterator();

        if (cursor.hasNext()) {
            collection.updateOne(and(eq("dni", dni), eq("tipoExamen", tipoExamen)), new Document("$set", new Document("nota", nota)));
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println("{\"mess\":\"Examen actualizado.\"}");
        } else {
            collection.insertOne(nota1);
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println("{\"mess\":\"Examen corregido.\"}");
        }

    }
}
