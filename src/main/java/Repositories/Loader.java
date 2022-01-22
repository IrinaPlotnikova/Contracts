package Repositories;

import Contracts.AbstractContract;
import Contracts.InternetContract;
import Contracts.PhoneContract;
import Contracts.TVContract;
import Persons.Person;
import Validation.ContractValidation;
import Validation.ValidationResultStatus;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Loader {

    /**
     * Сохраняет репозиторий как CSV файл.
     * */
    public static boolean saveAsCSVFile(Repository repository, String filePath){
        boolean isSaved = true;

        try{
            File file = new File(filePath);
            FileWriter outputFile = new FileWriter(file);
            AbstractContract[] contracts = repository.toArray();

            String header = "ДатаНачалаКонтракта;ДатаКонцаКонтракта;IdКонтракта;НомерКонтракта;IdВладельца;ИмяВладельца;" +
                    "ОтчествоВладельца;ФамилияВладельца;ДатаРожденияВладельца;ПолВладельца;ПаспортВладельца";
            outputFile.write(header);
            outputFile.write('\n');
            for (int i = 0; i < contracts.length; i++){
                outputFile.write(toCVSString(contracts[i]));
                outputFile.write('\n');
            }

            outputFile.close();
        }
        catch(Exception e) {
            isSaved = false;
        }
        return isSaved;
    }


    /**
     * Загружает данные в репозиторий из CSV файла.
     * */
    public static boolean loadFromCSVFile(Repository repository, String fileName){
        boolean isLoaded = true;
        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);
            reader.readLine(); // пропуск заголовка

            String newContract;
            while ((newContract = reader.readLine()) != null){
                AbstractContract contract = getContractFromCSVString(newContract);
                if (ContractValidation.validate(contract).getStatus() != ValidationResultStatus.Error){
                    repository.add(contract);
                }
            }

            fileReader.close();
        }
        catch(Exception e) {
            isLoaded = false;
        }
        return isLoaded;
    }

    /**
     * Преобразовывает контракт к формату CSV
     * */
    private static String toCVSString(AbstractContract contract){
        StringBuilder builder = new StringBuilder();

        builder.append(contract.getStartDate()).append(';')
                .append(contract.getEndDate()).append(';')
                .append(contract.getID()).append(';')
                .append(contract.getNumber()).append(';')
                .append(contract.getOwner().getID()).append(';')
                .append(contract.getOwner().getFirstName()).append(';')
                .append(contract.getOwner().getPatronymicName()).append(';')
                .append(contract.getOwner().getLastName()).append(';')
                .append(contract.getOwner().getDateOfBirth()).append(';')
                .append(contract.getOwner().isMale()).append(';')
                .append(contract.getOwner().getPassport()).append(';');

        if (contract.getClass() == TVContract.class){
            TVContract tmp = (TVContract)contract;
            builder.append("TV").append(';')
                    .append(tmp.getPackageName());
        }
        else if (contract.getClass() == InternetContract.class){
            InternetContract tmp = (InternetContract)contract;
            builder.append("Internet").append(';')
                    .append(tmp.getSpeed());
        }
        else{
            PhoneContract tmp = (PhoneContract)contract;
            builder.append("Phone").append(';')
                    .append(tmp.getNumberOfMinutes()).append(',')
                    .append(tmp.getNumberOfTexts()).append(',')
                    .append(tmp.getAmountOfData());
        }

        return builder.toString();
    }


    /**
     * Загружает контракт из строки формата CSV
     * */
    private static AbstractContract getContractFromCSVString(String contractInfo){
        String[] info = contractInfo.split(";");
        if (info.length < 13){
            return null;
        }

        int contractId;
        int contractNumber;
        LocalDate contractStartDate;
        LocalDate contractEndDate;
        Person owner;

        try {
            contractId = Integer.parseInt(info[2]);
        }
        catch (Exception e){
            contractId = 0;
        }

        try{
            contractNumber = Integer.parseInt(info[3]);
        }
        catch (Exception e){
            contractNumber = 0;
        }

        try{
            contractStartDate = LocalDate.parse(info[0]);
        }
        catch (Exception e){
            contractStartDate = null;
        }

        try{
            contractEndDate = LocalDate.parse(info[1]);
        }
        catch (Exception e){
            contractEndDate = null;
        }

        try{
            int ownerId = Integer.parseInt(info[4]);
            LocalDate dateOfBirth = LocalDate.parse(info[8]);
            owner = new Person(ownerId, info[5], info[6], info[7], dateOfBirth,
                    "true".equals(info[9]), info[10]);
        }
        catch (Exception e){
            owner = null;
        }

        if (info[11].equals("TV")){
            String packageName = info[12];
            return new TVContract(contractId, contractNumber, contractStartDate, contractEndDate, owner, packageName);
        }

        if (info[11].equals("Internet")){
            int speed;
            try{
                speed = Integer.parseInt(info[12]);
            }
            catch (Exception e){
                speed = 0;
            }
            return new InternetContract(contractId, contractNumber, contractStartDate, contractEndDate, owner, speed);
        }

        String[] values = info[12].split(",");
        int numberOfMinutes;
        int numberOfTexts;
        int amountOfData;

        try{
            numberOfMinutes = Integer.parseInt(values[0]);
        }
        catch (Exception e){
            numberOfMinutes = 0;
        }

        try {
            numberOfTexts = Integer.parseInt(values[1]);
        }
        catch (Exception e){
            numberOfTexts = 0;
        }

        try {
            amountOfData = Integer.parseInt(values[2]);
        }
        catch (Exception e){
            amountOfData = 0;
        }

        return new PhoneContract(contractId, contractNumber, contractStartDate, contractEndDate, owner, numberOfMinutes,
                numberOfTexts, amountOfData);
    }

}
