package com.yanzhen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanzhen.dao.RecordsMapper;
import com.yanzhen.model.PropertyType;
import com.yanzhen.model.RecordVo;
import com.yanzhen.model.Records;
import com.yanzhen.service.IRecordsService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kappy
 * @since 2020-10-28
 */
@Service
public class RecordsServiceImpl extends ServiceImpl<RecordsMapper, Records> implements IRecordsService {
    @Autowired
    private RecordsMapper recordsDao;

    @Autowired
    private PropertyTypeServiceImpl propertyTypeService;

    @Override
    public PageInfo<RecordVo> findRecordsAll(int page, int limit, RecordVo recordVo) {
        PageHelper.startPage(page,limit);
        List<RecordVo> list=recordsDao.queryRecordsAll(recordVo);
        PageInfo<RecordVo> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Records queryByHouIdAndTypeId(Integer houId, Integer typeId) {
        return recordsDao.queryByHouIdAndTypeId(houId,typeId);
    }

    @Override
    public int add(Records building){
        return baseMapper.insert(building);
    }



    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Records building){
        return baseMapper.updateById(building);
    }

    @Override
    public Records findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public void addRecords(Records records) {

        Integer houId = records.getHouseId();
        Integer typeId = records.getTypeId();
        Records rec = this.queryByHouIdAndTypeId(houId,typeId);
        if (rec != null) {
            records.setNum(rec.getNum());
        } else {
          records.setNum(0.0);
        }
        Integer startTime = records.getStartTime();
        Integer endTime = records.getEndTime();
        Date date = new Date();
        Date upDate = new Date();
        if (endTime < startTime) {
            date = DateUtils.setMonths(date, startTime);
            upDate = DateUtils.setMonths(upDate, endTime);
            upDate = DateUtils.addYears(upDate, 1);
        } else if (endTime.equals(startTime)){
            date = DateUtils.setMonths(date, startTime);
            endTime = endTime + 1;
            upDate = DateUtils.setMonths(upDate, endTime);
        } else if (endTime > startTime) {
            date = DateUtils.setMonths(date, startTime);
            upDate = DateUtils.setMonths(upDate, endTime);
        }
        records.setOnTime(date);
        records.setUpTime(upDate);
        PropertyType propertyType = propertyTypeService.findById(typeId.longValue());
        double sum = records.getNum2() - records.getNum();
        double price = sum * propertyType.getPrice();
        records.setStatus(0);
        records.setPrice(price);
        this.save(records);


    }


}
