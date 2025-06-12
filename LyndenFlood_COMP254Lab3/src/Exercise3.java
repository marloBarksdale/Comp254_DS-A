
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercise3 {

    //ArrayList to store absolute paths of the matching files
    private static List<String> absolutePathsOfFilesFound = new ArrayList<>();

    //file name given by the user
    private static String targetFilename;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path: ");
        String path = scanner.nextLine().trim();

        System.out.print("Enter the filename: ");
        targetFilename = scanner.nextLine().trim();

        searchFiles(new File(path));

        if (absolutePathsOfFilesFound.isEmpty()) {
            System.out.println("No files found");
        } else {
            System.out.println("Matching files:");
            for (String match : absolutePathsOfFilesFound) {
                System.out.println(match);
            }
        }

        scanner.close();
    }


    public static void searchFiles(File directory) {

        //Return if the file object is not valid
        if (!directory.exists()) return;

        //Get the list of files in a directory
        File[] files = directory.listFiles();

        //If the directory is empty, then return
        if (files == null) return;


        //For each file object in the directory, if it is a directory then enter and continue search else check if the current filename contains the search string
        for (File file : files) {


            if (file.isDirectory()) {

                searchFiles(file);
            } else if (file.getName().toLowerCase().contains(targetFilename.toLowerCase())) { //Will match any filename containing the target string. Not case sensitive
//                System.out.println(file.getAbsolutePath());
                //Add the absolute path of the matched file to the list
                absolutePathsOfFilesFound.add(file.getAbsolutePath());
            }
        }
    }
}
