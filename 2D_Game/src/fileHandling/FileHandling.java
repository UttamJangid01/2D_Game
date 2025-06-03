package fileHandling;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

import main.GamePanel;

public class FileHandling{
    GamePanel gp;
    private String Level = "";
    private String str1 = "src/map/", str2 = ".txt";
    public ArrayList<ArrayList<Integer>> map;

    public FileHandling(GamePanel gp){
        this.gp = gp;
        map = new ArrayList<>();
        setLevel();
        loadMap();
    }

    public void setLevel(){
        Level = str1 + gp.level.getLevel() + str2;
    }

    public void loadMap(){
        try(BufferedReader br = new BufferedReader(new FileReader(Level))){
            for(int row = 0; row < gp.row; row++){
                String line = br.readLine();
                ArrayList<Integer> rowLine = new ArrayList<>();
                for(int col = 0; col < gp.col; col++){
                    String number[] = line.split(" ");
                    rowLine.add(Integer.parseInt(number[col])); 
                }
                map.add(rowLine);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
