package com.flipkart.constant;

public class SQLCreateQueriesConstants {

    /*
    create table Admin
            (
                    userId varchar(255) not null
                    primary key
            );

    create table Notification
            (
                    notificationId int auto_increment
                    primary key,
                    studentId      int                                                       not null,
                    referenceId    varchar(255)                                              null,
    type           enum ('REGISTRATION', 'REGISTRATION_APPROVAL', 'PAYMENT') null,
    dateTime       datetime default (CURRENT_TIMESTAMP)                      not null
            );

    create table Professor
            (
                    userId      varchar(255) not null
    primary key,
    department  varchar(255) not null,
    designation varchar(255) not null
            );

    create table Student
            (
                    userId       varchar(255)         not null,
    branchName   varchar(255)         not null,
    batch        int                  not null,
    studentId    int auto_increment
    primary key,
    isApproved   tinyint(1)           not null,
    isRegistered tinyint(1) default 0 null
            );

    create table Payment
            (
                    id            int auto_increment
                    primary key,
                    studentId     int                                               not null,
                    referenceId   varchar(255)                                      not null,
    amount        int                                               not null,
    dateOfPayment datetime default (CURRENT_TIMESTAMP)              not null,
    modeOfPayment enum ('CREDIT_CARD', 'NET_BANKING', 'DEBIT_CARD') not null,
    constraint payment_ibfk_1
    foreign key (studentId) references Student (studentId)
            );

    create index studentId
    on Payment (studentId);

    create table User
            (
                    id       int auto_increment
                    primary key,
                    userId   varchar(255)                             not null,
    name     varchar(255)                             not null,
    password varchar(255)                             not null,
    role     enum ('STUDENT', 'PROFESSOR', 'ADMIN')   not null,
    gender   enum ('MALE', 'FEMALE', 'OTHER')         not null,
    address  varchar(255)                             not null,
    country  varchar(255) default 'INDIA'             not null,
    doj      datetime     default (CURRENT_TIMESTAMP) not null
            );

    create table courseCatalogue
            (
                    catalogId int auto_increment
                    primary key,
                    name      varchar(255) null
            );

    create table Course
            (
                    courseCode  int auto_increment
                    primary key,
                    courseName  varchar(255)           not null,
    catalogId   int                    null,
    professorId varchar(255)           null,
    isOffered   tinyint(1) default 1   null,
    seats       int        default 10  null,
    courseFee   int        default 500 null,
    constraint Course___fk
    foreign key (professorId) references Professor (userId)
            );

    create table registeredcourse
            (
                    id         int auto_increment
                    primary key,
                    studentId  int                                             not null,
                    courseCode int                                             not null,
                    grade      enum ('NA', 'F', 'E', 'D', 'C', 'B', 'A', 'EX') null,
    semester   int default 1                                   not null,
    constraint registeredcourse_ibfk_1
    foreign key (studentId) references Student (studentId),
    constraint registeredcourse_ibfk_2
    foreign key (courseCode) references Course (courseCode)
            );

    create index courseCode
    on registeredcourse (courseCode);

    create index studentId
    on registeredcourse (studentId);


    */

}
