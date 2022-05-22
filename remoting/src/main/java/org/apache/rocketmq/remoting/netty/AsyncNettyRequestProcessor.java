/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.rocketmq.remoting.netty;

import io.netty.channel.ChannelHandlerContext;
import org.apache.rocketmq.remoting.protocol.RemotingCommand;

public abstract class AsyncNettyRequestProcessor implements NettyRequestProcessor {

    /**
     * AsyncNettyRequestProcessor 中这个方法的逻辑其实是同步代码，除非子类覆盖这个方法。
     *
     * @param ctx              ctx
     * @param request          客户端数据封装对象
     * @param responseCallback callback 封装响应客户端的逻辑
     * @throws Exception
     */
    public void asyncProcessRequest(ChannelHandlerContext ctx, RemotingCommand request, RemotingResponseCallback responseCallback) throws Exception {
        /**
         * 看这个类的实现
         * @see org.apache.rocketmq.namesrv.processor.DefaultRequestProcessor#processRequest
         */
        RemotingCommand response = processRequest(ctx, request);

        // 拿到结果后，执行callback
        responseCallback.callback(response);
    }
}
