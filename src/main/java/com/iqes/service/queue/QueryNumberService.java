package com.iqes.service.queue;

/**
 * 叫号查询的service
 */

import com.iqes.entity.QueueInfo;
import com.iqes.repository.queue.QueueManagerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryNumberService {

    @Autowired
    private QueueManagerDao queueManagerDao;

    public QueueInfo queryNumber(){
        List<QueueInfo> queueInfos=queueManagerDao.getArrivingNumbers();
        QueueInfo queueInfo=new QueueInfo();

        for (QueueInfo q:queueInfos){
            //判断叫号次数
            if(q.getCallCount()<3){
                q.setCallCount(q.getCallCount()+1);
                queueManagerDao.save(q);
                queueInfo=q;
                break;
            }
        }
        return queueInfo;
    }

    public List<QueueInfo> getArrivingNumberByTableType(Long id){
        List<QueueInfo> queueInfos=queueManagerDao.getByExtractFlagAndAndTableType(id);
        return queueInfos;
    }
}
