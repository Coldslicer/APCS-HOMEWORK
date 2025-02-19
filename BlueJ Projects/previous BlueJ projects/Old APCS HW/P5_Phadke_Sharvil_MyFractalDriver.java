import gpdraw.*;
public class P5_Phadke_Sharvil_MyFractalDriver {
    public static void main(String... args) {
        SketchPad pad = new SketchPad(1000,1000);
        new P5_Phadke_Sharvil_MyFractal(pad).compile("{level: 6, y: -100, length:30,direction: 90,turn:15, width:5, width scales by level: true,length scales by level: true}","F[+D+D][-D-D]F[DD]").draw();
        new P5_Phadke_Sharvil_MyFractal(pad).compile("{x: -200, y: -300, level: 1,length:30,direction: 90,turn:15, width:1, width scales by level: true,length scales by level: true} F[+D+D][-D-D]F[DD]").draw();
        new P5_Phadke_Sharvil_MyFractal(pad).compile("{x: 0, y: -300, level: 2,length:30,direction: 90,turn:15, width:2, width scales by level: true,length scales by level: true} F[+D+D][-D-D]F[DD]").draw();
        new P5_Phadke_Sharvil_MyFractal(pad).compile("{x: 200, y: -300, level: 3,length:30,direction: 90,turn:15, width:3, width scales by level: true,length scales by level: true} F[+D+D][-D-D]F[DD]").draw();
        
        
        
        //really complicated plant: "F(30)[+D-F(30)[-D+F(50)[+D]D]+F(30)[-D+F(50)[+D]D]D]"
        //"F[+R(D,F)+R(F,D)][-R(D,F)-R(D,F)]F[DD]"
        //uglier tree: "F[+(15)D][-(20)D]"
        //random text can also make fractals (kind of) ")(YN){IENRWY{)W(ER&NC+)W{}Q(N&REW{PAONSDCKJSAGRPNEAC+COIREQIYNN+CSAD_RW{IADWY+IANSHKJNPOIEYRN+O=N+-AIE=O_ENR=NCAPEYWIRY"
    }
}
