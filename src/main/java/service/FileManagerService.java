package service;

import beans.FileManager;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManagerService {


    public FileManager manager = new FileManager();


    public FileManagerService() throws IOException {
    }

    public void hostFileToSet() {

        Stream<String> host = manager.getHostReader();
        manager.setHostSet(host.collect(Collectors.toSet()));

    }


    public void portFileToSet() {

        Stream<String> port = manager.getPortReader();
        manager.setPortSet(port.collect(Collectors.toSet()));

    }


}
