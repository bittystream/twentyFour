package cn.edu.cqu.twentyfour;

public class Poker {
    // 方块 A-M; 红桃 N-Z; 黑桃 a-m; 梅花 n-z;
    private char id;

    // num range: 1 - 13
    private int num;

    // cardtype 可选 黑 - S(Spade); 红 - H(Heart); 梅 - C(Club); 方 - D(Diamond)
    private char cardtype;
    private int color; // 1 黑 0 红

    public boolean isChosen;

    Poker(char ct, int n){
        this.isChosen = false;
        this.cardtype = ct;
        this.num = n;
        switch (ct){
            case 'S':
                id = (char)((int)'a'+num-1);
                color = 1;
                break;
            case 'C':
                id = (char)((int)'n'+num-1);
                color = 1;
                break;
            case 'D':
                id = (char)((int)'A'+num-1);
                color = 0;
                break;
            case 'H':
                id = (char)((int)'N'+num-1);
                color = 0;
                break;
        }
    }
    char getId(){
        return id;
    }
    int getColor(){ return color;}
}
