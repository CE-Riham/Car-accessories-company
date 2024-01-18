package helpers.comparators;

import classes.Starter;
import model.installation.Installer;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InstallerComparator {

    private InstallerComparator() {
        Starter.logger.info("Hi from installer comparator.");
    }

    public static List<Installer> sortInstallers(String field, boolean sortingType, List<Installer> installers) {
        Collections.sort(installers, chooseInstallerComparator(field, sortingType));
        return installers;
    }

    private static Comparator<Installer> chooseInstallerComparator(String field, boolean sortingType) {
        switch (field) {
            case "start hour":
                return sortInstallersByStartTime(sortingType);
            case "end hour":
                return sortInstallersByEndTime(sortingType);
            default:
                return sortInstallersByPricePerHourComparator(sortingType);
        }
    }

    private static Comparator<Installer> sortInstallersByPricePerHourComparator(boolean sortingType) {
        return (sortingType ? Comparator.comparingDouble(Installer::getPricePerHour)
                : Comparator.comparingDouble(Installer::getPricePerHour).reversed());
    }

    private static Comparator<Installer> sortInstallersByStartTime(boolean sortingType) {
        return (sortingType ? Comparator.comparing(Installer::getInstallationStartHour)
                : Comparator.comparing(Installer::getInstallationStartHour).reversed());
    }

    private static Comparator<Installer> sortInstallersByEndTime(boolean sortingType) {
        return (sortingType ? Comparator.comparing(Installer::getInstallationEndHour)
                : Comparator.comparing(Installer::getInstallationEndHour).reversed());
    }
}
