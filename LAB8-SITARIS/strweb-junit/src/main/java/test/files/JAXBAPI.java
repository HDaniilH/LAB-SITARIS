package test.files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.Session;
import test.files.entities.PersonEntity;
import test.files.xmlmarshalling.PersonXml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class JAXBAPI {
    static PersonEntity executeXML(String filename, Session session) throws IOException, JAXBException {
        PersonXml personXml = getQuery(filename);
        switch (personXml.getQtype()) {
            case CREATE -> create(personXml.getEntity(), session);
            case UPDATE -> update(personXml.getEntity(), session);
            case READ -> {
                return read(personXml.getEntity(), session);
            }
            case DELETE -> delete(personXml.getEntity(), session);
        }
        return null;
    }

    static PersonXml getQuery(String filename) throws IOException, JAXBException {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
        for (String line : lines) {
            stringBuilder.append(line);
        }
        JAXBContext jaxbContext = JAXBContext.newInstance(PersonXml.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (PersonXml) jaxbUnmarshaller.unmarshal(new StringReader(stringBuilder.toString()));
    }

    private static void create(PersonEntity entity, Session session) {
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
    }

    private static void update(PersonEntity entity, Session session) {
        session.beginTransaction();
        PersonEntity personEntity = session.get(PersonEntity.class, entity.getId());
        if (entity.getSurname() != null) {
            personEntity.setSurname(entity.getSurname());
        }
        if (entity.getAddress() != null) {
            personEntity.setAddress(entity.getAddress());
        }
        personEntity.setDutyBound(entity.getDutyBound());
        session.update(personEntity);
        session.getTransaction().commit();
    }

    private static PersonEntity read(PersonEntity entity, Session session) {
        return session.get(PersonEntity.class, entity.getId());
    }

    private static void delete(PersonEntity entity, Session session) {
        session.beginTransaction();
        PersonEntity personEntity = session.load(PersonEntity.class, entity.getId());
        session.delete(personEntity);
        session.getTransaction().commit();
    }

    static void addFromJson(@SuppressWarnings("SameParameterValue") String filename, Session session) throws IOException {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .setPrettyPrinting()
                .create();
        try (Reader reader = new FileReader(filename)) {
            PersonEntity personEntity = gson.fromJson(reader, PersonEntity.class);
            create(personEntity, session);
        }
    }

    static void dumpToJson(@SuppressWarnings("SameParameterValue") String filename, Session session, @SuppressWarnings("SameParameterValue") String ID) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        PersonEntity personEntity = session.find(PersonEntity.class, ID);
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(personEntity, writer);
        }
    }
}
