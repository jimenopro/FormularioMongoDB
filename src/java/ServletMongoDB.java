
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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
public class ServletMongoDB extends HttpServlet {

    private int b;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://jimenopro:admin@cluster0-qwfqm.mongodb.net/test");
        //Connect

        MongoClient mongoClient = new MongoClient(uri);
        //Create Database
        MongoDatabase database = mongoClient.getDatabase("prueba1");
        //Create collection
        MongoCollection<Document> collection = database.getCollection("prueba1");

        Document myDoc = collection.find().skip(b).first();
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            out.println(myDoc.toJson());
            mongoClient.close();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException,
            IOException {
        String n = request.getParameter("exam");
        b = Integer.parseInt(n);
        response.setContentType("application/json");
        PrintWriter pw = response.getWriter();
        pw.println("{\"mess\":\"Cargando examen...\"}");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    public void init() {

        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://jimenopro:admin@cluster0-qwfqm.mongodb.net/test");
        //Connect

        MongoClient mongoClient = new MongoClient(uri);
        //Create Database
        MongoDatabase database = mongoClient.getDatabase("prueba1");
        //Create collection
        MongoCollection<Document> collection = database.getCollection("prueba1");
        Document examen1 = new Document("examen1", "examen1");

        Document pregunta1 = new Document("pregunta1", "select-1")
                .append("titulo", "¿Qué significa illuminati en Latín?");

        Document respuesta1 = new Document("respuesta1", "select-1")
                .append("0", "Iluminados")
                .append("1", "Ilusorios")
                .append("2", "Ilusionistas")
                .append("answer", "0");

        Document pregunta2 = new Document("pregunta2", "radio-1")
                .append("titulo", "¿Que simbolo caracteriza a los Illuminati?");

        Document respuesta2 = new Document("respuesta2", "radio-1")
                .append("0", "Piramide")
                .append("1", "Corazon")
                .append("2", "Baston")
                .append("answer", "0");

        Document pregunta3 = new Document("pregunta3", "text-1")
                .append("titulo", "¿Eres Illuminati?");

        Document respuesta3 = new Document("respuesta3", "text-1")
                .append("answer", "Si");

        Document pregunta4 = new Document("pregunta4", "checkbox-1")
                .append("titulo", "¿Cual es el objetivo de la sociedad Illuminati?");

        Document respuesta4 = new Document("respuesta4", "checkbox-1")
                .append("0", "Gobernar el mundo")
                .append("1", "Paz y Amor")
                .append("2", "Vida Eterna")
                .append("answer", "0");

        Document pregunta5 = new Document("pregunta5", "multiple-1")
                .append("titulo", "¿Carecteristicas de la sociedad Illuminati?");

        Document respuesta5 = new Document("respuesta5", "multiple-1")
                .append("0", "Secreta")
                .append("1", "Selecta")
                .append("2", "Todo el mundo puede entrar")
                .append("answer1", "0")
                .append("answer2", "1");

        pregunta1.put("respuesta1", respuesta1);
        pregunta2.put("respuesta2", respuesta2);
        pregunta3.put("respuesta3", respuesta3);
        pregunta4.put("respuesta4", respuesta4);
        pregunta5.put("respuesta5", respuesta5);
        examen1.put("pregunta1", pregunta1);
        examen1.put("pregunta2", pregunta2);
        examen1.put("pregunta3", pregunta3);
        examen1.put("pregunta4", pregunta4);
        examen1.put("pregunta5", pregunta5);
        //Insert document
        collection.insertOne(examen1);
        Document examen2 = new Document("examen2", "examen2");

        Document pregunta6 = new Document("pregunta1", "select-1")
                .append("titulo", "¿Qué rio separa Francia y Alemania?");

        Document respuesta6 = new Document("respuesta1", "select-1")
                .append("0", "Guadalquivir")
                .append("1", "Tajo")
                .append("2", "Rin")
                .append("answer", "2");

        Document pregunta7 = new Document("pregunta2", "radio-1")
                .append("titulo", "¿Cuál es la tercera ciudad más poblada de España?");

        Document respuesta7 = new Document("respuesta2", "radio-1")
                .append("0", "Zaragoza")
                .append("1", "Sevilla")
                .append("2", "Valencia")
                .append("answer", "2");

        Document pregunta8 = new Document("pregunta3", "text-1")
                .append("titulo", "¿Es verdad que en España viven más de 45 millones de personas?");

        Document respuesta8 = new Document("respuesta3", "text-1")
                .append("answer", "Si");

        Document pregunta9 = new Document("pregunta4", "checkbox-1")
                .append("titulo", "¿En que año fue 1+1? ");

        Document respuesta9 = new Document("respuesta4", "checkbox-1")
                .append("0", "3")
                .append("1", "El fantástico Ralph")
                .append("2", "Azul")
                .append("answer", "2");

        Document pregunta10 = new Document("pregunta5", "multiple-1")
                .append("titulo", "¿Con qué paises hace frontera España?");

        Document respuesta10 = new Document("respuesta5", "multiple-1")
                .append("0", "Portugal")
                .append("1", "Alemania")
                .append("2", "Francia")
                .append("answer1", "0")
                .append("answer2", "2");

        pregunta6.put("respuesta1", respuesta6);
        pregunta7.put("respuesta2", respuesta7);
        pregunta8.put("respuesta3", respuesta8);
        pregunta9.put("respuesta4", respuesta9);
        pregunta10.put("respuesta5", respuesta10);
        examen2.put("pregunta1", pregunta6);
        examen2.put("pregunta2", pregunta7);
        examen2.put("pregunta3", pregunta8);
        examen2.put("pregunta4", pregunta9);
        examen2.put("pregunta5", pregunta10);
        //Insert document
        collection.insertOne(examen2);

        Document examen3 = new Document("examen3", "examen3");

        Document pregunta11 = new Document("pregunta1", "select-1")
                .append("titulo", "¿Qué relación tienen Mario y Wario?");

        Document respuesta11 = new Document("respuesta1", "select-1")
                .append("0", "Amigos")
                .append("1", "Pareja")
                .append("2", "Enemigos")
                .append("answer", "2");

        Document pregunta12 = new Document("pregunta2", "radio-1")
                .append("titulo", "¿Zelda es un chico o una chica?");

        Document respuesta12 = new Document("respuesta2", "radio-1")
                .append("0", "Chico")
                .append("1", "Chica")
                .append("2", "Caballo")
                .append("answer", "1");

        Document pregunta13 = new Document("pregunta3", "text-1")
                .append("titulo", "¿Skyrim es un buen juego?");

        Document respuesta13 = new Document("respuesta3", "text-1")
                .append("answer", "No");

        Document pregunta14 = new Document("pregunta4", "checkbox-1")
                .append("titulo", "¿Qué compañía compró King, desarrolladora de Candy Crush? ");

        Document respuesta14 = new Document("respuesta4", "checkbox-1")
                .append("0", "Activision")
                .append("1", "Ubisoft")
                .append("2", "EA")
                .append("answer", "0");

        Document pregunta15 = new Document("pregunta5", "multiple-1")
                .append("titulo", "¿Qué plataformas son malas para jugar?");

        Document respuesta15 = new Document("respuesta5", "multiple-1")
                .append("0", "PC")
                .append("1", "XBOX")
                .append("2", "PlayStation")
                .append("answer1", "1")
                .append("answer2", "2");

        pregunta11.put("respuesta1", respuesta11);
        pregunta12.put("respuesta2", respuesta12);
        pregunta13.put("respuesta3", respuesta13);
        pregunta14.put("respuesta4", respuesta14);
        pregunta15.put("respuesta5", respuesta15);
        examen3.put("pregunta1", pregunta11);
        examen3.put("pregunta2", pregunta12);
        examen3.put("pregunta3", pregunta13);
        examen3.put("pregunta4", pregunta14);
        examen3.put("pregunta5", pregunta15);
        //Insert document
        collection.insertOne(examen3);
        
        mongoClient.close();
    }

}
