package com.kele.penetrate.client.pipeline;

import com.kele.penetrate.factory.annotation.Autowired;
import com.kele.penetrate.factory.annotation.Recognizer;
import com.kele.penetrate.factory.annotation.Register;
import com.kele.penetrate.page.ClientLogPageManager;
import com.kele.penetrate.protocol.HandshakeResult;
import com.kele.penetrate.utils.Func;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("unused")
@Register
@Recognizer
public class HandshakeResultPipeline implements Func<Object, Boolean>
{
    @Autowired
    private ClientLogPageManager clientLogPageManager;

    @Override
    public Boolean func(Object msg)
    {
        if (msg instanceof HandshakeResult)
        {
            HandshakeResult handshakeResult = (HandshakeResult) msg;
            if (handshakeResult.isSuccess())
            {
                clientLogPageManager.addLog("启动成功 :");
                clientLogPageManager.addLog(handshakeResult.getFailMessages());
            }
            else
            {
                clientLogPageManager.addLog("启动失败 :");
                clientLogPageManager.addLog(handshakeResult.getFailMessages());
            }
            return true;
        }
        return false;
    }
}
