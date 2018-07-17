import java.util.Random;
import java.util.Scanner;
public class Game_day_hop {
    static void inmang(String[][] map) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(map[j][i] + " ");
            }
            System.out.println();
        }
    }
    static boolean checklose(int xb, int yb, String key,String map[][]){
        boolean ok=false;
        switch (key) {
            case "a" :
            case "d" :{
                if((map[(xb+10)%10][(yb+1+10)%10]=="|") || (map[(xb+10)%10][(yb-1+10)%10]=="|"))
                ok=true;
                break;
            }
            case "w":
            case "s":{
                if((map[(xb+1+10)%10][(yb+10)%10]=="|") || (map[(xb-1+10)%10][(yb+10)%10]=="|"))
                ok=true;
                break;
            }
        }
        return ok;
    }
    public static void main(String[] args) {
        // khoi tao mang
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int xp, yp;
        int xb, yb;
        int xd, yd;
        int ans=0;
        String[][] map = {
                {"*", "|", "*", "*", "|", "*", "|", "*", "|", "*"},
                {"*", "*", "*", "|", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "|", "*", "|", "*", "|"},
                {"*", "|", "*", "*", "|", "*", "*", "*", "*", "*"},
                {"|", "*", "*", "|", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "|", "*", "|", "*"},
                {"|", "*", "*", "|", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "|", "*", "*", "|", "*", "|", "*", "|"},
                {"*", "|", "*", "*", "|", "*", "|", "*", "*", "*"},
                {"|", "*", "*", "|", "*", "*", "*", "*", "|", "*"},
        };
        do{

            do {
                xp = random.nextInt(10);
                yp = random.nextInt(10);
            } while (map[xp][yp] == "|");
            map[xp][yp] = "P";
            do {
                xb = random.nextInt(10);
                yb = random.nextInt(10);
            } while (map[xb][yb] == "P" && map[xb][yb] == "|");
            map[xb][yb] = "B";
            do {
                xd = random.nextInt(10);
                yd = random.nextInt(10);
            } while (map[xd][yd] == "|" && map[xd][yd] == "P" && map[xb][yb] == "B");
            map[xd][yd] = "D";
            inmang(map);
            System.out.println("Doi map ko ?\n 1 : Yes   0 : No");
            do{
                ans=scanner.nextInt();
            }while(ans>1 || ans <0);
            if (ans==1){
                map[xp][yp] = "*";
                map[xb][yb] = "*";
                map[xd][yd] = "*";
            }
            else System.out.println(" Bat dau choi");
        }while(ans==1);
        while (true) {
            if(map[xd][yd]!="D"){
                map[xd][yd]="D";
            }
            inmang(map);
            String key;
            System.out.print(" nhap ky tu :");
            key = scanner.next();
            int movex = 0;
            int movey = 0;
            switch (key) {
                case "a": {
                    movex = -1;
                    break;
                }
                case "w": {
                    movey = -1;
                    break;
                }
                case "s": {
                    movey = 1;
                    break;
                }
                case "d": {
                    movex = 1;
                    break;
                }
                default: {
                    System.out.println("nhap sai");
                }
            }
            int tempxp = (xp + movex + 10) % 10;
            int tempyp = (yp + movey + 10) % 10;
            int tempxb = (xb + movex + 10) % 10;
            int tempyb = (yb + movey + 10) % 10;
            if (map[tempxp][tempyp] != "|") {
                if (map[tempxp][tempyp] == "B") {
                    if (map[tempxb][tempyb] != "|") {
                        map[xp][yp]="*";
                        map[xb][yb]="*";
                        xp = tempxp;
                        yp = tempyp;
                        xb = tempxb;
                        yb = tempyb;
                    }
                   else if(checklose(xb,yb,key,map)){
                        inmang(map);
                        System.out.println("Loser");
                        break;
                   }
                }
                else {
                    map[xp][yp]="*";
                    xp = tempxp;
                    yp = tempyp;
                }
            }
            map[xb][yb]="B";
            map[xp][yp]="P";
 /*           if(checklose(xb,yb,key,map)){
                inmang(map);
                System.out.println("Loser");
                break;
            }
  */
            if(map[xd][yd] == "B"){
                map[xd][yd]="D";
                inmang(map);
                System.out.println("You win");
                break;
            }
        }
    }
}
