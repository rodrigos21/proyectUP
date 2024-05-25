package arq.web.tp.integrador.orders.dto;

import arq.web.tp.integrador.products.dto.PurchaseOrder;

import java.util.List;

public class Report {

    List<PurchaseOrder> report;

    public List<PurchaseOrder> getReport() {
        return report;
    }

    public void setReport(List<PurchaseOrder> report) {
        this.report = report;
    }
}
