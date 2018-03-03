package beans;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class FileManager {
    FilePath file = new FilePath();

    private FileReader hostFile = new FileReader(file.HOST_FILE);
    private FileReader portFile = new FileReader(file.PORT_FILE);
    private BufferedReader hostReader = new BufferedReader(hostFile);
    private BufferedReader portReader = new BufferedReader(portFile);
    private FileWriter fileWriter = new FileWriter(file.VALID_FILE);

    private Set<String> hostSet = new HashSet<>();
    private Set<String> portSet = new HashSet<>();


    public FileManager() throws IOException {
    }


    public Stream<String> getHostReader() {
        return hostReader.lines();

    }

    public Stream<String> getPortReader() {

        return portReader.lines();

    }

    public FileWriter getFileWriter() {
        return fileWriter;
    }


    public Set<String> getHostSet() {
        return hostSet;
    }

    public void setHostSet(Set<String> hostSet) {
        this.hostSet = new HashSet<String>(hostSet);

    }

    public Set<String> getPortSet() {
        return portSet;
    }

    public void setPortSet(Set<String> portSet) {
        this.portSet = new HashSet<String>(portSet);
    }
}
