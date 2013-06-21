package ch.k42.auroraprime.quorgs;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 28.05.13
 * Time: 21:02
 * To change this template use File | Settings | File Templates.
 */
import java.util.ArrayList;
import java.util.List;


public class TextEffect extends NEOEffect {
    /**
     *
     * @param settings text | foregroundcolor| backgroundcolor | speed
     */
    public TextEffect(String[] settings) {
        super(settings);
        if(settings.length>3){
            this.text = settings[0];
            this.TEXT = NEOUtils.toNEOColor(settings[1]);
            this.BACK = NEOUtils.toNEOColor(settings[2]);
            int speed = Integer.parseInt(settings[3]);
            switch (speed) {
                case 1 : this.speed = FST; break;
                case 2 : this.speed = IMB; break;
                default: this.speed = SLO; break;
            }
        }else{
            this.text = "TextQuorg! Hi, Hello? Halph?! Ooooo nononono !!";
            this.TEXT = NEOUtils.NEO_RED;
            this.BACK = NEOUtils.NEO_OFF;
            this.speed = IMB;
        }

    }

    @Override
    public int[][] getArray() {
        return array;
    }

    //Define Colors
    static final int OFF = NEOUtils.NEO_OFF;
    static final int RED = NEOUtils.NEO_RED;
    static final int GRN = NEOUtils.NEO_GREEN;
    static final int BLU = NEOUtils.NEO_BLUE;
    static final int YEL = NEOUtils.NEO_YELLOW;
    static final int TUR = NEOUtils.NEO_TURK;
    static final int PUR = NEOUtils.NEO_PINK;
    static final int WHT = NEOUtils.NEO_WHITE;

    //Declare Arrays
    private List<Integer> hexArray = new ArrayList<Integer>();
    private List<int[]> clrArray = new ArrayList<int[]>();

    //Define Layout
    int TEXT = RED;
    int BACK = BLU;

    //Define Textvar
    String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


    //Define Speed
    static final int SLO = 500;
    static final int FST = 100;
    static final int IMB = 50;

    int speed = SLO;


    //Define Letters for Hexarray

    //404 Sign
    final int[] NF = {0xff, 0xd2, 0xbd, 0xd2,0xff};

    //Upper case	65
    final int[] A = {0x7f, 0x90, 0x90, 0x90, 0x7f};
    final int[] B = {0xff, 0x91, 0x91, 0x91, 0x6e};
    final int[] C = {0x7e, 0x81, 0x81, 0x81, 0x81};
    final int[] D = {0xff, 0x81, 0x81, 0x81, 0x7e};
    final int[] E = {0xff, 0x91, 0x91, 0x81, 0x81};
    final int[] F = {0xff, 0x90, 0x90, 0x80, 0x80};
    final int[] G = {0x7e, 0x81, 0x81, 0x91, 0x0e};
    final int[] H = {0xff, 0x10, 0x10, 0x10, 0xff};
    final int[] I = {0x81, 0x81, 0xff, 0x81, 0x81};
    final int[] J = {0x8e, 0x81, 0xfe, 0x80, 0x80};
    final int[] K = {0xff, 0x10, 0x28, 0x44, 0x83};
    final int[] L = {0xff, 0x01, 0x01, 0x01, 0x01};
    final int[] M = {0xff, 0x40, 0x30, 0x40, 0xff};
    final int[] N = {0xff, 0x60, 0x10, 0x0C, 0xff};
    final int[] O = {0x7e, 0x81, 0x81, 0x81, 0x7e};
    final int[] P = {0xff, 0x90, 0x90, 0x90, 0x60};
    final int[] Q = {0x7e, 0x81, 0x85, 0x82, 0x7d};
    final int[] R = {0xff, 0x90, 0x98, 0x94, 0x63};
    final int[] S = {0x61, 0x91, 0x91, 0x91, 0x8e};
    final int[] T = {0x80, 0x80, 0xff, 0x80, 0x80};
    final int[] U = {0xfe, 0x01, 0x01, 0x01, 0xfe};
    final int[] V = {0xf0, 0x0c, 0x03, 0x0c, 0xf0};
    final int[] W = {0xfc, 0x03, 0x0c, 0x03, 0xfc};
    final int[] X = {0xc7, 0x28, 0x10, 0x28, 0xc7};
    final int[] Y = {0xc0, 0x20, 0x1f, 0x20, 0xc0};
    final int[] Z = {0x87, 0x89, 0x91, 0xa1, 0xc1};

    //Lower case	97
    final int[] a = {0x02, 0x15, 0x15, 0x15, 0x0f};
    final int[] b = {0xff, 0x11, 0x11, 0x11, 0x0e};
    final int[] c = {0x0e, 0x11, 0x11, 0x11, 0x11};
    final int[] d = {0x0e, 0x11, 0x11, 0x11, 0xff};
    final int[] e = {0x0e, 0x15, 0x15, 0x15, 0x08};
    final int[] f = {0x10, 0x7f, 0x90, 0x90, 0x40};
    final int[] g = {0x09, 0x15, 0x15, 0x15, 0x0e};
    final int[] h = {0xff, 0x08, 0x10, 0x10, 0x0f};
    final int[] i = {0x01, 0x11, 0x5f, 0x01, 0x01};
    final int[] j = {0x02, 0x01, 0x11, 0x51, 0x1e};
    final int[] k = {0xff, 0x08, 0x08, 0x14, 0x13};
    final int[] l = {0x01, 0x81, 0xff, 0x01, 0x01};
    final int[] m = {0x1f, 0x10, 0x0c, 0x10, 0x0f};
    final int[] n = {0x1f, 0x10, 0x10, 0x10, 0x0f};
    final int[] o = {0x0e, 0x11, 0x11, 0x11, 0x0e};
    final int[] p = {0x1f, 0x14, 0x14, 0x14, 0x18};
    final int[] q = {0x18, 0x14, 0x14, 0x14, 0x1f};
    final int[] r = {0x1f, 0x80, 0x10, 0x10, 0x80};
    final int[] s = {0x81, 0x15, 0x15, 0x15, 0x12};
    final int[] t = {0x10, 0xfe, 0x11, 0x11, 0x02};
    final int[] u = {0x1e, 0x01, 0x01, 0x01, 0x1f};
    final int[] v = {0x18, 0x04, 0x03, 0x04, 0x18};
    final int[] w = {0x1c, 0x03, 0x04, 0x03, 0x1c};
    final int[] x = {0x11, 0x0a, 0x04, 0x0a, 0x11};
    final int[] y = {0x18, 0x05, 0x05, 0x05, 0x1e};
    final int[] z = {0x11, 0x13, 0x15, 0x19, 0x11};

    //Umlauts	192
    final int[] À = {0x1f, 0xa8, 0x68, 0x28, 0x1f};
    final int[] Á = {0x1f, 0x28, 0x68, 0xa8, 0x1f};
    final int[] Â = {0x1f, 0x68, 0xa8, 0x68, 0x1f};
    final int[] Ã = {0x4f, 0x94, 0x54, 0x37, 0x4f};
    final int[] Ä = {0x1f, 0xa8, 0x28, 0xa8, 0x1f};
    //199
    final int[] Ç = {0x78, 0x84, 0x85, 0x86, 0x84};
    final int[] È = {0x3f, 0xa9, 0x69, 0x21, 0x21};
    final int[] É = {0x3f, 0x29, 0x69, 0xa1, 0x21};
    final int[] Ê = {0x3f, 0x69, 0xa9, 0x61, 0x21};
    final int[] Ë = {0x3f, 0xa9, 0x29, 0xa1, 0x21};
    //209
    final int[] Ñ = {0x5f, 0x88, 0x44, 0x22, 0x5f};
    final int[] Ò = {0x1e, 0xa1, 0x61, 0x21, 0x1e};
    final int[] Ó = {0x1e, 0x21, 0x61, 0xa1, 0x1e};
    final int[] Ô = {0x1e, 0x61, 0xa1, 0x61, 0x1e};
    final int[] Õ = {0x4e, 0x91, 0x51, 0x31, 0x4e};
    final int[] Ö = {0x1e, 0xa1, 0x21, 0xa1, 0x1e};
    //216
    final int[] Ø = {0x7e, 0x82, 0xbd, 0xc1, 0x7e};
    final int[] Ù = {0x3e, 0x81, 0x41, 0x01, 0x3e};
    final int[] Ú = {0x3e, 0x01, 0x41, 0x81, 0x3e};
    final int[] Û = {0x3e, 0x41, 0x81, 0x41, 0x3e};
    final int[] Ü = {0x3e, 0x81, 0x01, 0x81, 0x3e};
    //223
    final int[] ß = {0x7f, 0x90, 0x91, 0x69, 0x06};
    final int[] à = {0x02, 0x95, 0x55, 0x15, 0x0f};
    final int[] á = {0x02, 0x15, 0x55, 0x95, 0x0f};
    final int[] â = {0x02, 0x55, 0x95, 0x55, 0x0f};
    final int[] ã = {0x42, 0x95, 0x55, 0x35, 0x4f};
    final int[] ä = {0x02, 0x55, 0x15, 0x55, 0x0f};
    //231
    final int[] ç = {0x18, 0x24, 0x25, 0x26, 0x24};
    final int[] è = {0x0e, 0x95, 0x55, 0x15, 0x08};
    final int[] é = {0x0e, 0x15, 0x55, 0x95, 0x08};
    final int[] ê = {0x0e, 0x55, 0x95, 0x55, 0x08};
    final int[] ë = {0x0e, 0x55, 0x15, 0x55, 0x08};
    //241
    final int[] ñ = {0x5f, 0x90, 0x50, 0x30, 0x4f};
    final int[] ò = {0x0e, 0x91, 0x51, 0x11, 0x0e};
    final int[] ó = {0x0e, 0x11, 0x51, 0x91, 0x0e};
    final int[] ô = {0x0e, 0x51, 0x91, 0x91, 0x0e};
    final int[] õ = {0x4e, 0x91, 0x51, 0x31, 0x4e};
    final int[] ö = {0x0e, 0x91, 0x11, 0x91, 0x0e};
    //248
    final int[] ø = {0x0e, 0x13, 0x15, 0x19, 0x0e};
    final int[] ù = {0x1e, 0x81, 0x41, 0x01, 0x1f};
    final int[] ú = {0x1e, 0x01, 0x41, 0x81, 0x1f};
    final int[] û = {0x1e, 0x41, 0x81, 0x41, 0x1f};
    final int[] ü = {0x1e, 0x41, 0x01, 0x41, 0x1f};

    //Digits	48
    final int[] d0 = {0x7e, 0x85, 0x99, 0xa1, 0x7e};
    final int[] d1 = {0x41, 0x81, 0xff, 0x01, 0x01};
    final int[] d2 = {0x47, 0x89, 0x91, 0x91, 0x61};
    final int[] d3 = {0x42, 0x81, 0x81, 0x91, 0x6e};
    final int[] d4 = {0x30, 0x70, 0x90, 0xff, 0x10};
    final int[] d5 = {0xf2, 0x91, 0x91, 0x91, 0x8e};
    final int[] d6 = {0x7e, 0x91, 0x91, 0x91, 0x4e};
    final int[] d7 = {0x80, 0x81, 0x9f, 0xa1, 0xc0};
    final int[] d8 = {0x6e, 0x91, 0x91, 0x91, 0x6e};
    final int[] d9 = {0x62, 0x91, 0x91, 0x91, 0x7e};


    //Symbols
    final int[] ssp = {0x00, 0x00, 0x00, 0x00, 0x00};
    final int[] sex = {0x00, 0x00, 0xfd, 0x00, 0x00};
    final int[] squ = {0x00, 0xc0, 0x00, 0xc0, 0x00};
    final int[] snu = {0x24, 0xff, 0x24, 0xff, 0x24};
    final int[]   $ = {0x62, 0x91, 0xff, 0x91, 0x4e};
    final int[] spr = {0x62, 0x64, 0x18, 0x26, 0x46};
    final int[] sam = {0x6e, 0x99, 0x95, 0x62, 0x05};
    final int[] sap = {0x00, 0x00, 0xc0, 0x00, 0x00};
    final int[] slp = {0x00, 0x00, 0x7e, 0x81, 0x00};
    final int[] srp = {0x00, 0x81, 0x7e, 0x00, 0x00};
    final int[] sas = {0x28, 0x30, 0xe0, 0x30, 0x28};
    final int[] spl = {0x10, 0x10, 0x7c, 0x10, 0x10};
    final int[] scm = {0x00, 0x01, 0x02, 0x00, 0x00};
    final int[] smi = {0x10, 0x10, 0x10, 0x10, 0x10};
    final int[] sfs = {0x00, 0x00, 0x01, 0x00, 0x00};
    final int[] ssl = {0x03, 0x04, 0x18, 0x20, 0xc0};

    final int[] sco = {0x00, 0x00, 0x09, 0x00, 0x00};
    final int[] ssc = {0x00, 0x01, 0x0a, 0x00, 0x00};
    final int[] slt = {0x04, 0x0a, 0x0a, 0x11, 0x11};
    final int[] seq = {0x12, 0x12, 0x12, 0x12, 0x12};
    final int[] sgt = {0x11, 0x11, 0x0a, 0x0a, 0x04};
    final int[] sqm = {0x40, 0x80, 0x8d, 0x90, 0x60};
    final int[] sat = {0x1e, 0x21, 0x2d, 0x25, 0x19};

    final int[] sls = {0x00, 0x00, 0xff, 0x81, 0x00};
    final int[] sbs = {0xc0, 0x20, 0x18, 0x04, 0x03};
    final int[] srs = {0x00, 0x81, 0xff, 0x00, 0x00};
    final int[] sci = {0x00, 0x40, 0x80, 0x40, 0x00};
    final int[] sll = {0x01, 0x01, 0x01, 0x01, 0x01};
    final int[] sgr = {0x00, 0x80, 0x40, 0x00, 0x00};

    final int[] slc = {0x00, 0x10, 0x6e, 0x81, 0x00};
    final int[] svl = {0x00, 0x00, 0xff, 0x00, 0x00};
    final int[] src = {0x00, 0x81, 0x6e, 0x10, 0x00};
    final int[] sti = {0x10, 0x20, 0x10, 0x08, 0x10};

    final int[] sie = {0x00, 0x00, 0xbf, 0x00, 0x00};
    final int[] sce = {0x1d, 0x26, 0x3a, 0x62, 0x14};
    final int[] spo = {0x11, 0x7e, 0x91, 0x81, 0x42};
    final int[] scu = {0x11, 0x0e, 0x0a, 0x0e, 0x11};
    final int[] sye = {0xca, 0x2a, 0x1f, 0x2a, 0xca};
    final int[] sbb = {0x00, 0x00, 0xe7, 0x00, 0x00};
    final int[] spa = {0x52, 0xa9, 0xa9, 0xa9, 0x96};
    final int[] sdi = {0x00, 0x80, 0x00, 0x80, 0x00};
    final int[] scr = {0x3e, 0x41, 0x5d, 0x5d, 0x3e};
    final int[] sfo = {0x00, 0xb0, 0xd0, 0x70, 0x00};
    final int[] slq = {0x04, 0x0a, 0x15, 0x0a, 0x11};
    final int[] sno = {0x10, 0x10, 0x10, 0x10, 0x18};
    final int[] sre = {0x3e, 0x41, 0x57, 0x49, 0x3e};
    final int[] sma = {0x00, 0x80, 0x80, 0x80, 0x00};
    final int[] sde = {0x60, 0x90, 0x90, 0x90, 0x60};
    final int[] spm = {0x12, 0x12, 0x7e, 0x12, 0x12};
    final int[] ss2 = {0x00, 0x48, 0x98, 0x68, 0x00};
    final int[] ss3 = {0x00, 0x88, 0xa8, 0x50, 0x00};
    final int[] sac = {0x00, 0x00, 0x40, 0x80, 0x00};
    final int[] µ =   {0x1f, 0x02, 0x02, 0x02, 0x1c};
    final int[] spi = {0x60, 0xf0, 0xff, 0x80, 0xff};
    final int[] smd = {0x00, 0x00, 0x10, 0x00, 0x00};
    final int[] scd = {0x00, 0x00, 0x05, 0x02, 0x00};
    final int[] ss1 = {0x00, 0x48, 0xf8, 0x08, 0x00};
    final int[] srq = {0x11, 0x0a, 0x15, 0x0a, 0x04};
    final int[] s14 = {0x40, 0xf0, 0x0e, 0x02, 0x0f};
    final int[] s12 = {0x40, 0xf0, 0x09, 0x0b, 0x05};
    final int[] s34 = {0x90, 0xf0, 0x0e, 0x02, 0x0f};
    final int[] siq = {0x05, 0x09, 0xb1, 0x01, 0x02};

    final int[] smu = {0x44, 0x28, 0x10, 0x28, 0x44};
    final int[] sds = {0x10, 0x10, 0x54, 0x10, 0x10};

    //Greek
    //TODO


    //Define Letter Arrays
    final int[][] upper_case = {/*65*/A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z/*90*/};
    final int[][] lower_case = {/*97*/a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z/*122*/};

    final int[][] umlauts_upper = {/*192*/À,Á,Â,Ã,Ä,/*196 Å,Æ,
								     199+2*/Ç,È,É,Ê,Ë,/*203 Ì,Í,Î,Ï,Ð,
								     209+7*/Ñ,Ò,Ó,Ô,Õ,Ö,/*214 ×,
								     216+8*/Ø,Ù,Ú,Û,Ü,/*220 Ý,Þ*/};

    final int[][] umlauts_lower = {/*223*/ß,à,á,â,ã,ä,/*228 å,æ,
									 231+2*/ç,è,é,ê,ë,/*235 ì,í,î,ï,ð,
									 241+7*/ñ,ò,ó,ô,õ,ö,/*246 ÷,
									 248+8*/ø,ù,ú,û,ü,/*252 ý,þ,ÿ*/};

    final int[][] digits = {/*48*/d0,d1,d2,d3,d4,d5,d6,d7,d8,d9/*57*/};

    final int[][] symbols = {/*32*/ssp,sex,squ,snu,$,spr,sam,sap,slp,srp,sas,spl,scm,smi,sfs,ssl,/*47 <digits>
							   58+10*/sco,ssc,slt,seq,sgt,sqm,sat,/*64 <uppercase>
							   91+36*/sls,sbs,srs,sci,sll,sgr,/*96 <lowercase>
							   123+62*/slc,svl,src,sti,/*126 ...
							   161+96*/sie,sce,spo,scu,sye,sbb,spa,sdi,scr,sfo,slq,sno,ssp,sre,sma,sde,spm,ss2,ss3,sac,µ,spi,smd,scd,ss1,sde,srq,s14,s12,s34,siq/*191*/};
    final int[][] greek = {};


    @Override
    public void run() {

		/*
		 * Array format: array[line][column]
		 * HexArray format: hexArray[hexcolumn]
		 */
        setTextArray(text);

        while (!EXIT)
        {
            //create array from color array
            for (int i=0; i<clrArray.size()-8; i++)
            {
                for(int col=0; col<8; col++)
                    for(int line=0; line<8; line++)
                        array[line][col] = clrArray.get(col+i)[line];

                //wait for next step
                try {
                    sleep(speed);
                } catch (InterruptedException e) {}
            }
        }
    }



    private void setTextArray(String text) {
        //First screen is empty
        for (int i=0;i<8;i++)
            hexArray.add(0);

        //parse the Letters
        for (int i=0; i<text.length(); i++)
        {
            int letter = text.charAt(i);
            int[] sign = NF; //sets 404 sign as default

            //-error block-
            if (letter < 32);

                //Check for symbol block 1/5
            else if (letter <= 47)
                sign = symbols[letter-32];

                //Check for digits
            else if (letter <= 57)
                sign = digits[letter-46];

                //Check for symbol block 2/5
            else if (letter <= 64)
                sign = symbols[letter-32-10];

                //Check for upper case
            else if (letter <= 90)
                sign = upper_case[letter-65];

                //Check for symbol block 3/5
            else if (letter <= 96)
                sign = symbols[letter-32-36];

                //Check for lower case
            else if (letter <= 122)
                sign = lower_case[letter-97];

                //Check for symbol block 4/5
            else if (letter <= 126)
                sign = symbols[letter-32-62];

                //-error block-
            else if (letter < 161);

                //Check for symbol block 5/5
            else if (letter <= 191)
                sign = symbols[letter-32-96];


                //Check for umlaut A-Block
            else if (letter <= 196)
                sign = umlauts_upper[letter-192];

            else if (letter <= 198)
                sign = A;

                //Check for umlaut E-Block
            else if (letter <= 203)
                sign = umlauts_upper[letter-192-2];

            else if (letter <= 207)
                sign = I;

            else if (letter == 208)
                sign = D;

                //Check for umlaut O-Block
            else if (letter <= 214)
                sign = umlauts_upper[letter-192-7];

            else if (letter == 215)
                sign = smu;

                //Check for umlaut U-Block
            else if (letter <= 220)
                sign = umlauts_upper[letter-192-8];

            else if (letter <= 222)
                sign = Y;

                //Check for umlaut a-Block
            else if (letter <= 228)
                sign = umlauts_lower[letter-223];

            else if (letter <= 230)
                sign = a;

                //Check for umlaut e-Block
            else if (letter <= 235)
                sign = umlauts_lower[letter-223-2];

            else if (letter <= 239)
                sign = this.i;

            else if (letter == 240)
                sign = d;

                //Check for umlaut o-Block
            else if (letter <= 246)
                sign = umlauts_lower[letter-223-7];

            else if (letter == 247)
                sign = sds;

                //Check for umlaut u-Block
            else if (letter <= 252)
                sign = umlauts_lower[letter-223-8];

            else if (letter <= 255)
                sign = y;


            //Check for greeks
            //TODO


            //Now write the sign into the hexArray
            for (int j=0; j<5; j++)
                hexArray.add(sign[j]);

            //add space
            hexArray.add(0);
        }

        //add last space
        for(int i=0; i<8; i++)
            hexArray.add(0);

        //create color array
        for (int col=0; col<hexArray.size(); col++)
        {
            //save column here
            int[] tempcol = new int[8];

            for (int line=0;line<8;line++)
            {
                int mask = (0x80 >> line);
                if ((hexArray.get(col) & mask) != 0)
                    tempcol[line] = TEXT;
                else
                    tempcol[line] = BACK;
            }
            //write saved col into array
            clrArray.add(tempcol);
        }
    }

}