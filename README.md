# CCINFOM-Group5-DbApp

🔗 [Database Project Proposal](https://docs.google.com/document/d/1-jrOARWG2kotG-TS0ANylUJbeU9Pu4Oy/edit)  
🔗 [Schema Scratch Sheet](https://docs.google.com/spreadsheets/d/1hMwmDPGuGJo0CpJH_TSuASftIagkDVdCUQSiE0-XhAQ/edit?gid=0#gid=0)  


## 📍 TODO
- [x] Create EERD design (using lucidchart for convenience)
    - [x] Draft design
        🔗 [Lucidchart EERD](https://lucid.app/lucidchart/fd84e567-7d5e-4e4f-ac70-80523924ceb4/edit?viewport_loc=-326%2C-210%2C2096%2C1127%2C0_0&invitationId=inv_b714da36-9bd6-4d95-911b-d0ffe0834c44)
    - [x] Use UML design with crow foot
    - [x] Add relation table
- [ ] Create SQL Queries
    - [x] Make tables in line with proposal, schema, and EERD
    - [x] Make sample queries for testing
    - [x] Ensure normalization for tables (recheck EERD and schema)
    - [ ] Create queries for transactions and reports
      Transactions
        - [x] Query: Booking an appointment
        - [x] Query: Updating therapist availability
        - [x] Query: Paying for a service
        - [ ] Query: Reporting client feedback
      Reports
        - [x] Query: Monthly Appointment Summary
        - [ ] Query: Service Popularity Report
        - [ ] Query: Service Report
        - [x] Query: Revenue Report
    - [ ] Forward engineer the tables using mysql workbench
- [ ] Create Java app
    - [ ] Create simple interface first
    - [ ] Connect Java to MYSQL local host
    - [ ] Test queries with executeQuery();
    - [ ] Ensure queries for transactions and reports can be done through Java
- [ ] Enhance interface (once connected and queries work) [OPTIONAL]
    - [ ] Allow user option for transaction and report
