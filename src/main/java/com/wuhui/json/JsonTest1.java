package com.wuhui.json;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class JsonTest1 {

    public static void main(String[] args) {
        StockQuantityChangeDTO stockQuantityChangeDTO1 = new StockQuantityChangeDTO();
        List<StockQuantityDifferenceDTO> stockQuantityDifferenceDTOS1 = Arrays.asList(new StockQuantityDifferenceDTO("06f2cb77-7163-4a7c-ae11-1b2d3f241daf", 2, 0, 0, false, null),
                new StockQuantityDifferenceDTO("2a28aa1d-dda5-4f1b-b576-b1c3d166e862", 1, 0, 0, false, null));
        StockQuantityTraceDTO stockQuantityTraceDTO1 = new StockQuantityTraceDTO("59958ac6-9d31-4206-9336-169cea6b2219", 0, 110, "ec15ce83-8e74-4e90-9eac-cb45e00a8319");
        fillingFieldValue(stockQuantityChangeDTO1, "ec15ce84-8e74-4e90-9eac-cb45e00a8319", stockQuantityDifferenceDTOS1, null, null, stockQuantityTraceDTO1);

        StockQuantityChangeDTO stockQuantityChangeDTO2 = new StockQuantityChangeDTO();
        List<StockQuantityDifferenceDTO> stockQuantityDifferenceDTOS2 = Arrays.asList(new StockQuantityDifferenceDTO("ad18156a-db33-4e7b-aa78-05e836879964", 2, 0, 0, false, null),
                new StockQuantityDifferenceDTO("63f88fa1-80a3-4fca-a0c5-766d217d4385", 1, 0, 0, false, null));
        StockQuantityTraceDTO stockQuantityTraceDTO2 = new StockQuantityTraceDTO("f0e054e3-8dcc-4434-8f1b-368050142bd8", 0, 110, "ac5d38c6-3dca-4cb4-9656-0802162c91ab");
        fillingFieldValue(stockQuantityChangeDTO2, "ac5d38c7-3dca-4cb4-9656-0802162c91ab", stockQuantityDifferenceDTOS2, null, null, stockQuantityTraceDTO2);

        StockQuantityChangeDTO stockQuantityChangeDTO3 = new StockQuantityChangeDTO();
        List<StockQuantityDifferenceDTO> stockQuantityDifferenceDTOS3 = Arrays.asList(new StockQuantityDifferenceDTO("5f23c33c-4848-4214-9587-5583b5200684", 1, 0, 0, false, null),
                new StockQuantityDifferenceDTO("704ac96c-12d5-4ee5-9d3c-14df460064bf", 1, 0, 0, false, null), new StockQuantityDifferenceDTO("37a6e22a-d99d-44f7-9eb7-d946e36161ee", 1, 0, 0, false, null),
                new StockQuantityDifferenceDTO("974e0c52-808d-4eaf-87b1-d46f90fba1e9", 1, 0, 0, false, null), new StockQuantityDifferenceDTO("2ece6f6d-74dd-4a88-b4d8-6a166fa1182d", 1, 0, 0, false, null),
                new StockQuantityDifferenceDTO("3e393ddb-8363-4c83-8028-a55c9f57970e", 1, 0, 0, false, null));
        StockQuantityTraceDTO stockQuantityTraceDTO3 = new StockQuantityTraceDTO("c31410a1-e7f4-4c40-8e6f-ece0763be359", 0, 110, "8823f6de-0003-4da5-a71d-a8d5fd3aeecc");
        fillingFieldValue(stockQuantityChangeDTO3, "8823f6df-0003-4da5-a71d-a8d5fd3aeecc", stockQuantityDifferenceDTOS3, null, null, stockQuantityTraceDTO3);

        System.out.println(JSON.toJSONString(stockQuantityChangeDTO1));
        System.out.println(JSON.toJSONString(stockQuantityChangeDTO2));
        System.out.println(JSON.toJSONString(stockQuantityChangeDTO3));
    }

    private static void fillingFieldValue(StockQuantityChangeDTO stockQuantityChangeDTO,
                                          String uniqueId, List<StockQuantityDifferenceDTO> stockQuantityDifferenceDTOS,
                                          List<NewStoreProductBatchDTO> newBatches, List<StockQuantityAdditionalLogDTO> stockQuantityAdditionalLogs, StockQuantityTraceDTO stockQuantityTrace) {
        stockQuantityChangeDTO.setUniqueId(uniqueId);
        stockQuantityChangeDTO.setStockQuantityDifferences(stockQuantityDifferenceDTOS);
        stockQuantityChangeDTO.setNewBatches(newBatches);
        stockQuantityChangeDTO.setStockQuantityAdditionalLogs(stockQuantityAdditionalLogs);
        stockQuantityChangeDTO.setStockQuantityTrace(stockQuantityTrace);
    }
}

//StockQuantityChangeDTO(uniqueId=ec15ce84-8e74-4e90-9eac-cb45e00a8319, stockQuantityDifferences=[StockQuantityDifferenceDTO(batchId=06f2cb77-7163-4a7c-ae11-1b2d3f241daf, count=2, countReserved=null, changeType=0, tryEnableStoreProduct=null, stockQuantityTrace=null), StockQuantityDifferenceDTO(batchId=2a28aa1d-dda5-4f1b-b576-b1c3d166e862, count=1, countReserved=null, changeType=0, tryEnableStoreProduct=null, stockQuantityTrace=null)], newBatches=null, stockQuantityAdditionalLogs=null, stockQuantityTrace=StockQuantityTraceDTO(userId=59958ac6-9d31-4206-9336-169cea6b2219, origin=0, refType=110, refId=ec15ce83-8e74-4e90-9eac-cb45e00a8319)
//stockQuantityChangeDTO=StockQuantityChangeDTO(uniqueId=ac5d38c7-3dca-4cb4-9656-0802162c91ab, stockQuantityDifferences=[StockQuantityDifferenceDTO(batchId=ad18156a-db33-4e7b-aa78-05e836879964, count=2, countReserved=null, changeType=0, tryEnableStoreProduct=null, stockQuantityTrace=null), StockQuantityDifferenceDTO(batchId=63f88fa1-80a3-4fca-a0c5-766d217d4385, count=1, countReserved=null, changeType=0, tryEnableStoreProduct=null, stockQuantityTrace=null)], newBatches=null, stockQuantityAdditionalLogs=null, stockQuantityTrace=StockQuantityTraceDTO(userId=f0e054e3-8dcc-4434-8f1b-368050142bd8, origin=0, refType=110, refId=ac5d38c6-3dca-4cb4-9656-0802162c91ab)
//stockQuantityChangeDTO=StockQuantityChangeDTO(uniqueId=ac5d38c7-3dca-4cb4-9656-0802162c91ab, stockQuantityDifferences=[StockQuantityDifferenceDTO(batchId=ad18156a-db33-4e7b-aa78-05e836879964, count=2, countReserved=null, changeType=0, tryEnableStoreProduct=null, stockQuantityTrace=null), StockQuantityDifferenceDTO(batchId=63f88fa1-80a3-4fca-a0c5-766d217d4385, count=1, countReserved=null, changeType=0, tryEnableStoreProduct=null, stockQuantityTrace=null)], newBatches=null, stockQuantityAdditionalLogs=null, stockQuantityTrace=StockQuantityTraceDTO(userId=f0e054e3-8dcc-4434-8f1b-368050142bd8, origin=0, refType=110, refId=ac5d38c6-3dca-4cb4-9656-0802162c91ab)
//stockQuantityChangeDTO=StockQuantityChangeDTO(uniqueId=ac5d38c7-3dca-4cb4-9656-0802162c91ab, stockQuantityDifferences=[StockQuantityDifferenceDTO(batchId=ad18156a-db33-4e7b-aa78-05e836879964, count=2, countReserved=null, changeType=0, tryEnableStoreProduct=null, stockQuantityTrace=null), StockQuantityDifferenceDTO(batchId=63f88fa1-80a3-4fca-a0c5-766d217d4385, count=1, countReserved=null, changeType=0, tryEnableStoreProduct=null, stockQuantityTrace=null)], newBatches=null, stockQuantityAdditionalLogs=null, stockQuantityTrace=StockQuantityTraceDTO(userId=f0e054e3-8dcc-4434-8f1b-368050142bd8, origin=0, refType=110, refId=ac5d38c6-3dca-4cb4-9656-0802162c91ab)
//StockQuantityChangeDTO(uniqueId=8823f6df-0003-4da5-a71d-a8d5fd3aeecc, stockQuantityDifferences=[StockQuantityDifferenceDTO(batchId=5f23c33c-4848-4214-9587-5583b5200684, count=1, countReserved=null, changeType=0, tryEnableStoreProduct=null, stockQuantityTrace=null), StockQuantityDifferenceDTO(batchId=704ac96c-12d5-4ee5-9d3c-14df460064bf, count=1, countReserved=null, changeType=0, tryEnableStoreProduct=null, stockQuantityTrace=null), StockQuantityDifferenceDTO(batchId=37a6e22a-d99d-44f7-9eb7-d946e36161ee, count=1, countReserved=null, changeType=0, tryEnableStoreProduct=null, stockQuantityTrace=null), StockQuantityDifferenceDTO(batchId=974e0c52-808d-4eaf-87b1-d46f90fba1e9, count=1, countReserved=null, changeType=0, tryEnableStoreProduct=null, stockQuantityTrace=null), StockQuantityDifferenceDTO(batchId=2ece6f6d-74dd-4a88-b4d8-6a166fa1182d, count=1, countReserved=null, changeType=0, tryEnableStoreProduct=null, stockQuantityTrace=null), StockQuantityDifferenceDTO(batchId=3e393ddb-8363-4c83-8028-a55c9f57970e, count=1, countReserved=null, changeType=0, tryEnableStoreProduct=null, stockQuantityTrace=null)], newBatches=null, stockQuantityAdditionalLogs=null, stockQuantityTrace=StockQuantityTraceDTO(userId=c31410a1-e7f4-4c40-8e6f-ece0763be359, origin=0, refType=110, refId=8823f6de-0003-4da5-a71d-a8d5fd3aeecc)
@Data
class StockQuantityChangeDTO {

    private String uniqueId;

    private List<StockQuantityDifferenceDTO> stockQuantityDifferences;

    private List<NewStoreProductBatchDTO> newBatches;

    private List<StockQuantityAdditionalLogDTO> stockQuantityAdditionalLogs;

    private StockQuantityTraceDTO stockQuantityTrace;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class StockQuantityDifferenceDTO {
    private String batchId;

    private Integer count;

    private Integer countReserved;

    private Integer changeType;

    private Boolean tryEnableStoreProduct;

    private StockQuantityTraceDTO stockQuantityTrace;
}

@Data
class NewStoreProductBatchDTO {

    private String id;

    private String storeProductId;

    private String supplierId;

    private String num;

    private Integer price;

    private Long timeProduction;

    private Long timeWarehousing;

    private String shelfCodes;

    private String shelfId;

    private String cardId;

    private Integer batchType;

    private Integer stockQuantity;

    private Integer stockQuantityReserved;

    private Boolean isDiscount;

    private Integer costUnit;

    private Integer costBatch;

    private String receiptChecklistId;

    private Long timeExpired;

    private Double unitGrossMargin;

    private Boolean isPreVerdict;

    private Double taxRate;

    private Long customTimeLastDelivery;

    private Boolean tryEnableStoreProduct;

    private String batchIdFrom;
}

@Data
class StockQuantityAdditionalLogDTO implements Serializable {

    private String batchId;

    private Integer count;

    private Integer changeType;

    private Integer origin;

    private Integer refType;

    private String refId;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class StockQuantityTraceDTO {

    private String userId;

    private Integer origin;

    private Integer refType;

    private String refId;
}