package Validation;

import Contracts.AbstractContract;
import Contracts.InternetContract;
import Contracts.PhoneContract;
import Contracts.TVContract;

public class ContractValidation {
    public static ValidationResult validate(AbstractContract contract){
        if (contract == null){
            return new ValidationResult(ValidationResultStatus.Error, "Контракт == null");
        }

        if (contract.getStartDate() == null){
            return new ValidationResult(ValidationResultStatus.Error, "Не указана дата начала контракта");
        }

        if (contract.getEndDate() == null){
            return new ValidationResult(ValidationResultStatus.Error, "Не указана дата конца контракта");
        }

        if (contract.getOwner() == null){
            return new ValidationResult(ValidationResultStatus.Error, "Не указана владелец контракта");
        }

        if (contract.getNumber() <= 0){
            return new ValidationResult(ValidationResultStatus.RedRisk, "Номер контракта <= 0");
        }

        if (contract.getStartDate().isAfter(contract.getEndDate())){
            return new ValidationResult(ValidationResultStatus.RedRisk, "Не указана владелец контракта");
        }

        if (contract.getClass() == InternetContract.class){
            InternetContract internetContract = (InternetContract) contract;
            if (internetContract.getSpeed() <= 0){
                return new ValidationResult(ValidationResultStatus.RedRisk, "Скорость <= 0");
            }
        } else if (contract.getClass() == TVContract.class) {
            TVContract tvContract = (TVContract) contract;
            if (tvContract.getPackageName() == null || tvContract.getPackageName().equals("")){
                return new ValidationResult(ValidationResultStatus.RedRisk, "Название пакета не указано");
            }
        } else if (contract.getClass() == PhoneContract.class){
            PhoneContract phoneContract = (PhoneContract) contract;
            if (phoneContract.getAmountOfData() < 0){
                return new ValidationResult(ValidationResultStatus.RedRisk, "Количество трафика < 0");
            }
            if (phoneContract.getNumberOfMinutes() < 0){
                return new ValidationResult(ValidationResultStatus.RedRisk, "Количество минут < 0");
            }
            if (phoneContract.getNumberOfTexts() < 0){
                return new ValidationResult(ValidationResultStatus.RedRisk, "Количество сообщений < 0");
            }
        }

        return new ValidationResult(ValidationResultStatus.Ok, "");
    }
}
