package enums;

public enum ENUM_PERSON_TITEL {

    DR("Dr."), PROF("Prof."), PROF_DR("Prof. Dr."), DIPL("Dipl."), DIPL_ING("Dipl.-Ing.");
    private String titel;
    private ENUM_PERSON_TITEL(String titel){
        this.titel = titel;
    }
}
