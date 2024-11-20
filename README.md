# CCINFOM-Group5-DbApp

🔗 [Database Project Proposal](https://docs.google.com/document/d/1-jrOARWG2kotG-TS0ANylUJbeU9Pu4Oy/edit)
🔗 [Schema Scratch Sheet](https://docs.google.com/spreadsheets/d/1hMwmDPGuGJo0CpJH_TSuASftIagkDVdCUQSiE0-XhAQ/edit?gid=0#gid=0)


## 📍 TODO
- [ ] Create EERD design (using lucidchart for convenience)
    - [ ] Draft design (current task: roe working on this help out by putting your entities/tables)
          - 🔗 [Lucidchart EERD](https://lucid.app/lucidchart/fd84e567-7d5e-4e4f-ac70-80523924ceb4/edit?viewport_loc=-326%2C-210%2C2096%2C1127%2C0_0&invitationId=inv_b714da36-9bd6-4d95-911b-d0ffe0834c44)
    - [ ] Use Tangkeko's shitty crow foot DB design
    - [ ] Add relation table
- [ ] Create SQL Queries
    - [ ] Make tables in line with proposal, schema, and EERD
    - [ ] Make sample queries for testing
    - [ ] Ensure normalization for tables (recheck EERD and schema)
    - [ ] Create queries for transactions and reports
      Transactions
        - [ ] Query: Booking an appointment
        - [ ] Query: Updating therapist availability
        - [ ] Query: Paying for a service
        - [ ] Query: Reporting client feedback
      Reports
        - [ ] Query: Monthly Appointment Summary
        - [ ] Query: Service Popularity Report
        - [ ] Query: Service Report
        - [ ] Query: Revenue Report
- [ ] Create Java app
    - [ ] Create simple interface first
    - [ ] Connect Java to MYSQL local host
    - [ ] Test queries with executeQuery();
    - [ ] Ensure queries for transactions and reports can be done through Java
- [ ] Enhance interface (once connected and queries work) [OPTIONAL]
    - [ ] Allow user option for transaction and report
