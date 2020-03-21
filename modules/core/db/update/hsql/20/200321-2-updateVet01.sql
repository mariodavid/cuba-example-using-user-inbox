alter table PETCLINIC_VET add constraint FK_PETCLINIC_VET_USER foreign key (USER_ID) references SEC_USER(ID);
create index IDX_PETCLINIC_VET_USER on PETCLINIC_VET (USER_ID);
