package helpers.filters;

import classes.Starter;
import helpers.Generator;
import model.installation.Installer;

import java.sql.Time;
import java.util.List;

public class InstallerFilter {
    private InstallerFilter() {
        Starter.logger.info("Hi from installer filter.");
    }

    public static List<Installer> filterInstallersBy(String field, String fieldValue, List<Installer> installers) {
        if (fieldValue.isEmpty()) return installers;
        return switch (field) {
            case "installerUsername" -> searchByUsername(installers, fieldValue);
            case "price less than or equal" -> searchByPriceLessThanOrEqual(installers, fieldValue);
            case "works on day" -> searchByWorkingDay(installers, fieldValue);
            case "works at hour" -> searchByWorkingHour(installers, fieldValue);
            default -> installers;
        };
    }

    private static List<Installer> searchByUsername(List<Installer> installers, String username) {
        return installers.stream()
                .filter(installer -> username.equals(installer.getUsername()))
                .toList();
    }

    private static List<Installer> searchByPriceLessThanOrEqual(List<Installer> installers, String price) {
        return installers.stream()
                .filter(installer -> installer.getPricePerHour() <= Double.parseDouble(price))
                .toList();
    }

    private static List<Installer> searchByWorkingDay(List<Installer> installers, String day) {
        return installers.stream()
                .filter(installer -> installer.worksOnDay(day))
                .toList();
    }


    private static List<Installer> searchByWorkingHour(List<Installer> installers, String inputTime) {
        if (inputTime.length() == 1)
            inputTime = "0" + inputTime;
        Time time = Generator.stringToTimeConvertor(inputTime);
        return installers.stream()
                .filter(installer -> installer.worksAtTime(time))
                .toList();
    }
}
