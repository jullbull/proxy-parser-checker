package activities;

import beans.FileManager;
import beans.ProxyData;
import service.FileManagerService;
import service.ProxyService;

import java.io.IOException;

public class ProxyChecker {

    private FileManagerService fileService = new FileManagerService();
    private ProxyService proxyService = new ProxyService();
    private FileManager fileManager = new FileManager();


    public ProxyChecker() throws IOException {
    }

    public void runProxyChecker() throws IOException {

        fileService.hostFileToSet();
        fileService.portFileToSet();


        proxyService.checkStatusCode(fileService.manager.getHostSet(), fileService.manager.getPortSet());

        for (ProxyData proxyData : proxyService.getProxyAlive()) {
            proxyService.convert(proxyData.getHost(), proxyData.getPort());

        }

        for (ProxyData proxyData : proxyService.getDetails()) {

            fileManager.getFileWriter().write(proxyData.getHost() + ":" + proxyData.getPort() + " " + proxyData.getCountry() +
                    " " + proxyData.getCity() + " " + proxyData.getZip() + System.lineSeparator());
        }
        fileManager.getFileWriter().close();


    }
}



