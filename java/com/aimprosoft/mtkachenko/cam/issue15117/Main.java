package com.aimprosoft.mtkachenko.cam.issue15117;

import com.aimprosoft.mtkachenko.cam.common.model.Constraint;
import com.aimprosoft.mtkachenko.cam.common.service.TemplateEngine;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mikhail Tkachenko
 */
public class Main {

    public static String outputDir = "/home/misha/Work/Projects/motive-CAM/resources/issue15117/test-case-templates";
    public static String baseTemplatePath = "/home/misha/Work/Projects/motive-CAM/resources/issue15117/base-template/PDSProcessPartsInventory.xml";

    public static List<Constraint> constraints = Arrays.asList(

            /* 1-PI-xx constraints */

            new Constraint("1-PI-01", "not(./star:DestinationNameCode[matches(.,'^PL$')])", "//star:ApplicationArea/star:Destination,This Test Case requires the Destination Name Code element in the Destination component to be equal to 'PL'."),
            new Constraint("1-PI-02", "not(./star:DestinationNameCode[matches(.,'^BD$')])", "//star:ApplicationArea/star:Destination,This Test Case requires the Destination Name Code element in the Destination component to be equal to 'BD'."),
            new Constraint("1-PI-03", "not(./star:DestinationNameCode[matches(.,'^KA$')])", "//star:ApplicationArea/star:Destination,This Test Case requires the Destination Name Code element in the Destination component to be equal to 'KA'."),

            new Constraint("1-PI-04", "not(string(./star:QuantitySold)", "//star:PartsInventoryLine,This Test Case requires the Quantity Sold element to be present in the PartsInventory Line component."),
            new Constraint("1-PI-05", "not(string(./star:QuantityOfLostSale)", "//star:PartsInventoryLine,This Test Case requires the Quantity Of Lost Sale element to be present in the PartsInventory Line component."),

            new Constraint("1-PI-06", "number(./star:QuantityOnOrder) &lt; 0 or number(./star:QuantityOnOrder) &gt; 8", "//star:PartsInventoryLine,This Test Case requires the Quantity On Order element in the PartsInventory Line component to be in range [0-8]."),
            new Constraint("1-PI-07", "number(./star:QuantityReOrderPoint) &lt; 0 or number(./star:QuantityReOrderPoint) &gt; 8", "//star:PartsInventoryLine,This Test Case requires the Quantity Re Order Point element in the PartsInventory Line component to be in range [0-8]."),

            new Constraint("1-PI-08", "not(string(./star:QuantityTwelveMonthSales)", "//star:PartsInventoryLine,This Test Case requires the Quantity Twelve Month Sales element to be present in the PartsInventory Line component."),
            new Constraint("1-PI-09", "not(string(./star:QuantityTwelveMonthLostSales)", "//star:PartsInventoryLine,This Test Case requires the Quantity Twelve Month Lost Sales element to be present in the PartsInventory Line component."),
            new Constraint("1-PI-10", "not(string(./star:BackOrderQuantity)", "//star:PartsInventoryLine,This Test Case requires the Back Order Quantity element to be present in the PartsInventory Line component."),
            new Constraint("1-PI-11", "not(string(./star:QuantityOfReturn)", "//star:PartsInventoryLine,This Test Case requires the Quantity Of Return element to be present in the PartsInventory Line component."),
            new Constraint("1-PI-12", "not(string(./star:QuantityDealerPartStocking)", "//star:PartsInventoryLine,This Test Case requires the Quantity Dealer Part Stocking element to be present in the PartsInventory Line component."),
            new Constraint("1-PI-13", "not(string(./star:QuantityAvailable)", "//star:PartsInventoryLine,This Test Case requires the Quantity Available element to be present in the PartsInventory Line component."),
            new Constraint("1-PI-14", "not(string(./star:LastSoldDate)", "//star:PartsInventoryLine,This Test Case requires the Last Sold Date element to be present in the PartsInventory Line component."),
            new Constraint("1-PI-15", "not(string(./star:QuantityUserMin)", "//star:PartsInventoryLine,This Test Case requires the Quantity User Min element to be present in the PartsInventory Line component."),
            new Constraint("1-PI-16", "not(string(./star:QuantityUserMax)", "//star:PartsInventoryLine,This Test Case requires the Quantity User Max element to be present in the PartsInventory Line component."),
            new Constraint("1-PI-17", "not(string(./star:QuantityBestStockingLevel)", "//star:PartsInventoryLine,This Test Case requires the Quantity Best Stocking Level element to be present in the PartsInventory Line component."),
            new Constraint("1-PI-18", "not(string(./star:LastReceiptDate)", "//star:PartsInventoryLine,This Test Case requires the Last Receipt Date element to be present in the PartsInventory Line component."),
            new Constraint("1-PI-19", "not(string(./star:DateOfLastOrder)", "//star:PartsInventoryLine,This Test Case requires the Date Of Last Order element to be present in the PartsInventory Line component."),
            new Constraint("1-PI-20", "not(string(./star:LastPhysicalInventoryDate)", "//star:PartsInventoryLine,This Test Case requires the Last Physical Inventory Date element to be present in the PartsInventory Line component.")

    );


    public static void main(String[] args) {
        TemplateEngine templateEngine = new TemplateEngine(baseTemplatePath, outputDir);
        templateEngine.createTemplates(constraints);
    }


}
