package com.javaer.app;

import static com.javaer.app.MyCreate.combination;

public class Main {

    private static char[] chars = new char[]{
            '\u0600', '\u0601','\u0602','\u0603','\u0604','\u0605','\u0606','\u0607','\u0608','\u0609','\u060a','\u060b','\u060c','\u060d','\u060e','\u060f',
            '\u0610', '\u0611','\u0612','\u0613','\u0614','\u0615','\u0616','\u0617','\u0618','\u0619','\u061a','\u061b','\u061c','\u061d','\u061e','\u060f',
            '\u0620', '\u0621','\u0622','\u0623','\u0624','\u0625','\u0626','\u0627','\u0628','\u0629','\u062a','\u062b','\u062c','\u062d','\u062e','\u060f',
            '\u0630', '\u0631','\u0632','\u0633','\u0634','\u0635','\u0636','\u0637','\u0638','\u0639','\u063a','\u063b','\u063c','\u063d','\u063e','\u060f',
            '\u0640', '\u0641','\u0642','\u0643','\u0644','\u0645','\u0646','\u0647','\u0648','\u0649','\u064a','\u064b','\u064c','\u064d','\u064e','\u060f',
            '\u0650', '\u0651','\u0652','\u0653','\u0654','\u0655','\u0656','\u0657','\u0658','\u0659','\u065a','\u065b','\u065c','\u065d','\u065e','\u060f',
            '\u0660', '\u0661','\u0662','\u0663','\u0664','\u0665','\u0666','\u0667','\u0668','\u0669','\u066a','\u066b','\u066c','\u066d','\u066e','\u060f',
            '\u0670', '\u0671','\u0672','\u0673','\u0674','\u0675','\u0676','\u0677','\u0678','\u0679','\u067a','\u067b','\u067c','\u067d','\u067e','\u060f',
            '\u0680', '\u0681','\u0682','\u0683','\u0684','\u0685','\u0686','\u0687','\u0688','\u0689','\u068a','\u068b','\u068c','\u068d','\u068e','\u060f',
            '\u0690', '\u0691','\u0692','\u0693','\u0694','\u0695','\u0696','\u0697','\u0698','\u0699','\u069a','\u069b','\u069c','\u069d','\u069e','\u060f',
            '\u06a0', '\u06a1','\u06a2','\u06a3','\u06a4','\u06a5','\u06a6','\u06a7','\u06a8','\u06a9','\u06aa','\u06ab','\u06ac','\u06ad','\u06ae','\u060f',
            '\u06b0', '\u06b1','\u06b2','\u06b3','\u06b4','\u06b5','\u06b6','\u06b7','\u06b8','\u06b9','\u06ba','\u06bb','\u06bc','\u06bd','\u06be','\u060f',
            '\u06c0', '\u06c1','\u06c2','\u06c3','\u06c4','\u06c5','\u06c6','\u06c7','\u06c8','\u06c9','\u06ca','\u06cb','\u06cc','\u06cd','\u06ce','\u060f',
            '\u06d0', '\u06d1','\u06d2','\u06d3','\u06d4','\u06d5','\u06d6','\u06d7','\u06d8','\u06d9','\u06da','\u06db','\u06dc','\u06dd','\u06de','\u060f',
            '\u06e0', '\u06e1','\u06e2','\u06e3','\u06e4','\u06e5','\u06e6','\u06e7','\u06e8','\u06e9','\u06ea','\u06eb','\u06ec','\u06ed','\u06ee','\u060f',
            '\u06f0', '\u06f1','\u06f2','\u06f3','\u06f4','\u06f5','\u06f6','\u06f7','\u06f8','\u06f9','\u06fa','\u06fb','\u06fc','\u06fd','\u06fe','\u060f',

    };

    public static void main(String[] args) {
//        ChineseProguard.start();
//        new com.wrbug.proguardcreater.SpecificCharacterProguard("proguard-o0O.txt", chars).start();
//        new SpecificCharacterProguard("proguard-1il.txt", '1', 'i', 'l', 'L', 'I', '丨').start();
//        new SpecificCharacterProguard("proguard-socialism.txt", "富强民主文明和谐自由平等公正法治爱国敬业诚信友善".toCharArray()).start();
//        combination(chars);
//        int i = 0;
//        for (char c : chars){
//            i += 1;
//            System.out.print(c);
//            if (i % 16 == 0){
//                System.out.print("\n");
//            }
//        }
        MyCreate.start();
    }
}
