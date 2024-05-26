package arq.web.tp.integrador.orders.dto;

import arq.web.tp.integrador.products.dto.OrderReport;

import java.util.List;

public class Report {

    List<OrderReport> report;

    public List<OrderReport> getReport() {
        return report;
    }

    public void setReport(List<OrderReport> report) {
        this.report = report;
    }
}
