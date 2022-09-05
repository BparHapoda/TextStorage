

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Console {
    private final ArrayList<Page> pages = new ArrayList<>();

    private final int line;
    private final int symbolsInLine;
    private int index = 1;

    public Console(int line, int symbolsInLine) {
        this.line = line;
        this.symbolsInLine = symbolsInLine;

    }

    public ArrayList<Page> create(String text) {
        ArrayList<String> lines = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder textLine = new StringBuilder();

        StringTokenizer stringTokenizer = new StringTokenizer(text, " ");
        while (stringTokenizer.hasMoreTokens()) {
            stringBuilder.delete(0, stringBuilder.length());
            stringBuilder.append(stringTokenizer.nextToken());
            stringBuilder.append(" ");
            if (((textLine.length() + stringBuilder.length()) >= symbolsInLine) || !stringTokenizer.hasMoreTokens()) {
                textLine.append(stringBuilder);
                lines.add(textLine.toString());
                textLine.delete(0, textLine.length());
            } else {
                textLine.append(stringBuilder);
                stringBuilder.delete(0, stringBuilder.length());
            }

            if (lines.size() == line || !stringTokenizer.hasMoreTokens()) {
                pages.add(new Page(lines));
                lines = new ArrayList<>();
            }
        }

        return pages;
    }


    public void outputPageText(ArrayList<Page> pages, boolean withFind) {
        if (pages.size() == 0) {
            System.out.println("Информация не найдена");
            return;
        }
        printPage(pages, 1);
        Menu menu2 = new Menu("Открытие файла", true);
        menu2.add("предъидущая страница", () -> {
            --index;
            if (index < 1) {
                index = 1;
            }
            printPage(pages, index);
        });
        menu2.add("следующая страница", () -> {
            ++index;
            if (index > pages.size()) {
                index = pages.size();
            }
            printPage(pages, index);
        });

        if (withFind) {
            menu2.add("Поиск по документу", () -> {
                index = 1;
                ArrayList<Page> find = find();
                if (find.size() > 0) {
                    outputPageText(find, false);
                } else {
                    System.out.println("Искомая строка не найдена");
                    menu2.setExit(true);
                }
            });
            menu2.add("Поиск и замена", () -> {
                TextDoc textDoc = new TextDoc();
                System.out.println("Введите заменяемую строку:");
                String input = inputString();
                System.out.println("Введите строку для замены:");
                String output = inputString();
                outputPageText(findAndReplace(pages, input, output), false);
            });
        }
        menu2.add("Выход", () ->{ printPage(pages,1);menu2.setExit(true);});
        menu2.run();


    }

    public void printPage(ArrayList<Page> pages, int index) {

        if (index < 1) {
            index = 1;
        } else if (index > pages.size()) {
            index = pages.size();
        }
        pages.get(index - 1).getText().forEach(System.out::println);
    }

    public ArrayList<Page> find() {
        ArrayList<Page> pagesFound = new ArrayList<>();
        System.out.println("Введите поисковую строку :");
        String string = inputString();
        pages.stream().peek((x) -> {
            if (pageContains(x, string)) {
                pagesFound.add(addSelection(x, string));
            }
        }).collect(Collectors.toList());
        return pagesFound;
    }
    public ArrayList<Page> findAndReplace (ArrayList <Page>pages,String lookingString,String replaceString){

        for (Page page : pages){
            for (int i=0;i<page.getText().size();i++
            ){
                String string=page.getText().get(i).replaceAll(lookingString,replaceString);
                page.getText().set(i,string);

            }
        }

        return  pages;
    }

    public boolean pageContains(Page page, String lookingString) {

        List<String> find = page.getText().stream().filter(x -> x.contains(lookingString)).toList();
        return find.size() != 0;
    }

    public Page addSelection(Page page, String lookingString) {
        String replacement = "\033[43m" + lookingString + "\u001B[0m";
        for (int i = 0; i < page.getText().size(); i++) {
            if (page.getText().get(i).contains(lookingString)) {
                String x = page.getText().get(i).replaceAll(lookingString, replacement);
                page.getText().set(i, x);
            }
        }
        return page;
    }
    public static String inputString() {

        Scanner scanner = new Scanner(System.in);
        String string = returnInputResult( scanner.nextLine());

        return string;
    }


    public static String inputText() throws IOException {

        StringBuilder sb = new StringBuilder();
        String text;
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        while (true) {
            text = bufferedReader.readLine();
            if (text.equals("ESC")) {
                break;
            }
            sb.append(text);
        }


        return sb.toString();
    }
    public static String returnInputResult (String string){

        if (string==null){throw new NullPointerException();}
        return string;}
}