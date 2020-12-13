import java.io.*;

public class File {

    private String path;

    public File(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String fileReader() {

        String str = "";

        try {

            BufferedReader file = new BufferedReader(new FileReader(path));
            String aux = "";
            str = file.readLine();

            while (aux != null && str != null) {

                str = str.concat(aux);
                aux = file.readLine();
            }

            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str;
    }

    public void fileWriter(String str) {

        try {

            BufferedWriter file = new BufferedWriter(new FileWriter(path, true));
            file.write(str + "\n");
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
