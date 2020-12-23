package poi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Project;
import models.Report;
import models.Risk;
import models.Task;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;


public class WordWriter {

    private ObservableList<Project> projectsList = FXCollections.observableArrayList();
    private ObservableList<Task> tasksList = FXCollections.observableArrayList();
    private ObservableList<Risk> risksList = FXCollections.observableArrayList();

    public WordWriter(ObservableList<Project> projectsList, ObservableList<Task> tasksList, ObservableList<Risk> risksList) {
        this.projectsList = projectsList;
        this.tasksList = tasksList;
        this.risksList = risksList;
    }

    public WordWriter(){
    }

    public void createWordReport() {
        try {
            // создаем модель docx документа,
            // к которой будем прикручивать наполнение (колонтитулы, текст)
            XWPFDocument docxModel = new XWPFDocument();
            CTSectPr ctSectPr = docxModel.getDocument().getBody().addNewSectPr();
            // получаем экземпляр XWPFHeaderFooterPolicy для работы с колонтитулами
            XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(docxModel, ctSectPr);

            // создаем верхний колонтитул Word файла
            CTP ctpHeaderModel = createHeaderModel("ProjectManager          Создан:" + LocalDate.now().toString());
            // устанавливаем сформированный верхний
            // колонтитул в модель документа Word
            XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeaderModel, docxModel);
            headerFooterPolicy.createHeader(
                    XWPFHeaderFooterPolicy.DEFAULT,
                    new XWPFParagraph[]{headerParagraph}
            );

            // создаем нижний колонтитул docx файла
            CTP ctpFooterModel = createFooterModel("Project Manager - ProjectReport AICGTrade 2020 ");
            // устанавливаем сформированый нижний
            // колонтитул в модель документа Word
            XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooterModel, docxModel);
            headerFooterPolicy.createFooter(
                    XWPFHeaderFooterPolicy.DEFAULT,
                    new XWPFParagraph[]{footerParagraph}
            );

            // создаем обычный параграф, который будет расположен слева,
            // будет синим курсивом со шрифтом 25 размера
            XWPFParagraph bodyParagraph = docxModel.createParagraph();
            bodyParagraph.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun paragraphConfig = bodyParagraph.createRun();
          //  paragraphConfig.setItalic(true);
            paragraphConfig.setFontSize(14);
            // HEX цвет без решетки #
            paragraphConfig.setColor("06357a");
            paragraphConfig.setText("Новый текстовый документ"
            );

            // сохраняем модель docx документа в файл
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
            fileChooser.setTitle("Save Document");//Заголовок диалога
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Word (*.docx)", "*.docx");//Расширение
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(stage);//Указываем текущую сцену CodeNote.mainStage

            FileOutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
            docxModel.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
/*
    public  void createWordReport2() throws IOException {
        //Blank Document
        // создаем модель docx документа,
        // к которой будем прикручивать наполнение (колонтитулы, текст)
        XWPFDocument docxModel = new XWPFDocument();
        CTSectPr ctSectPr = docxModel.getDocument().getBody().addNewSectPr();
        // получаем экземпляр XWPFHeaderFooterPolicy для работы с колонтитулами
        XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(docxModel, ctSectPr);

        // создаем верхний колонтитул Word файла
        CTP ctpHeaderModel = createHeaderModel("ProjectManager          Создан:" + LocalDate.now().toString());
        // устанавливаем сформированный верхний
        // колонтитул в модель документа Word
        XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeaderModel, docxModel);
        headerFooterPolicy.createHeader(
                XWPFHeaderFooterPolicy.DEFAULT,
                new XWPFParagraph[]{headerParagraph}
        );

        // создаем нижний колонтитул docx файла
        CTP ctpFooterModel = createFooterModel("Project Manager - ProjectReport AICGTrade 2020 ");
        // устанавливаем сформированый нижний
        // колонтитул в модель документа Word
        XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooterModel, docxModel);
        headerFooterPolicy.createFooter(
                XWPFHeaderFooterPolicy.DEFAULT,
                new XWPFParagraph[]{footerParagraph}
        );

        // создаем обычный параграф, который будет расположен слева,
        // будет синим курсивом со шрифтом 25 размера
        XWPFParagraph bodyParagraph = docxModel.createParagraph();
        bodyParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun paragraphConfig = bodyParagraph.createRun();
        //  paragraphConfig.setItalic(true);
        paragraphConfig.setFontSize(16);
        // HEX цвет без решетки #
        paragraphConfig.setColor("06357a");
        paragraphConfig.setText(
                "Отчет о статусе проекта"
        );


        //Write first Text in the beginning
        XWPFParagraph para = docxModel.createParagraph();
        XWPFRun run = para.createRun();
        run.setText("Название проекта: " + "APM Serises");
        //Write first Text in the beginning
        XWPFParagraph para2 = docxModel.createParagraph();
        XWPFRun run2 = para.createRun();
        run2.setText("Срок внедрения: " + "22.12.2022");
        //Write first Text in the beginning
        XWPFParagraph para3 = docxModel.createParagraph();
        XWPFRun run3 = para.createRun();
        run3.setText("Стоимость: " + "2000");
        //Write first Text in the beginning
        XWPFParagraph para4 = docxModel.createParagraph();
        XWPFRun run4 = para.createRun();
        run4.setText("Описание: " + "проект для производительности" );



        XWPFParagraph para6 = docxModel.createParagraph();
        XWPFRun run6 = para.createRun();
        run4.setText("Задачи проета" );

        //create table

        for (Contact contact : contacts) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(contact.firstName);
            row.createCell(1).setCellValue(contact.lastName);
            row.createCell(2).setCellValue(contact.email);
            row.createCell(3).setCellValue(contact.dateOfBirth);
        }
        XWPFTable table = docxModel.createTable();
        //create first row
        XWPFTableRow tableRowOne = table.getRow(0);
        tableRowOne.getCell(0).setText("Название задчи");tableRowOne.addNewTableCell().setText("аафпкуфп");tableRowOne.addNewTableCell().setText("акфаукцф");
        XWPFTableRow tableRowTwo = table.createRow();
        tableRowTwo.getCell(0).setText(" 0");tableRowTwo.getCell(1).setText("0");tableRowTwo.getCell(2).setText("0");


        //Write first Text in the beginning
        XWPFParagraph para5 = docxModel.createParagraph();
        XWPFRun run5 = para.createRun();
        run5.setText("Риски проекта\n\n" );

        //create table2
        XWPFTable table2 = docxModel.createTable();
        //create first row
        XWPFTableRow tableRowOne2 = table2.getRow(0);
        tableRowOne2.getCell(0).setText("col one, row one");tableRowOne2.addNewTableCell().setText("col two, row one");tableRowOne2.addNewTableCell().setText("col three, row one");
        //create second row
        XWPFTableRow tableRowTwo2 = table2.createRow();
        tableRowTwo2.getCell(0).setText("col one, row two");tableRowTwo2.getCell(1).setText("col two, row two");tableRowTwo2.getCell(2).setText("col three, row two");
        //create third row
        XWPFTableRow tableRowThree2 = table2.createRow();
        tableRowThree2.getCell(0).setText("col one, row three");tableRowThree2.getCell(1).setText("col two, row three");tableRowThree2.getCell(2).setText("col three, row three");


        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Save Document");//Заголовок диалога
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Word (*.docx)", "*.docx");//Расширение
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(stage);//Указываем текущую сцену CodeNote.mainStage

        FileOutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
        docxModel.write(outputStream);
        outputStream.close();
    }*/
    private void dialogAlert(String str){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(str);
        alert.setHeaderText(null);
        alert.setContentText(str);
        alert.showAndWait();
    }
    private static CTP createFooterModel(String footerContent) {
        // создаем футер или нижний колонтитул
        CTP ctpFooterModel = CTP.Factory.newInstance();
        CTR ctrFooterModel = ctpFooterModel.addNewR();
        CTText cttFooter = ctrFooterModel.addNewT();

        cttFooter.setStringValue(footerContent);
        return ctpFooterModel;
    }

    private static CTP createHeaderModel(String headerContent) {
        // создаем хедер или верхний колонтитул
        CTP ctpHeaderModel = CTP.Factory.newInstance();
        CTR ctrHeaderModel = ctpHeaderModel.addNewR();
        CTText cttHeader = ctrHeaderModel.addNewT();

        cttHeader.setStringValue(headerContent);
        return ctpHeaderModel;
    }
}