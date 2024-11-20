# CCINFOM-Group5-DbApp

- Google Docs: [https://docs.google.com/document/d/1-jrOARWG2kotG-TS0ANylUJbeU9Pu4Oy/edit](url)
- Google Sheets Draft Schema: [https://docs.google.com/spreadsheets/d/1hMwmDPGuGJo0CpJH_TSuASftIagkDVdCUQSiE0-XhAQ/edit?gid=0#gid=0](url)

## TODO
- [ ] Create EERD design (using draw.io for convenience)
    - [ ] Add Tangkeko's shitty crow foot DB design
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
