package cn.qinguide.f5web.via.business.bus;

import com.kunminx.architecture.business.bus.BaseBus;

import cn.qinguide.f5web.via.business.request.IAboutRequest;
import cn.qinguide.f5web.via.business.request.IDownloadRequest;
import cn.qinguide.f5web.via.business.request.IHomeRequest;
import cn.qinguide.f5web.via.business.request.IInjectRequest;
import cn.qinguide.f5web.via.business.request.IMainRequest;
import cn.qinguide.f5web.via.business.request.IUploadRequest;

public class MainBus extends BaseBus {

    public static IMainRequest main() {
        return (IMainRequest) getRequest(IMainRequest.class);
    }

    public static IHomeRequest home() {
        return (IHomeRequest) getRequest(IHomeRequest.class);
    }

    public static IDownloadRequest download() {
        return (IDownloadRequest) getRequest(IDownloadRequest.class);
    }

    public static IInjectRequest inject() {
        return (IInjectRequest) getRequest(IInjectRequest.class);
    }

    public static IUploadRequest upload() {
        return (IUploadRequest) getRequest(IUploadRequest.class);
    }


    public static IAboutRequest about() {
        return (IAboutRequest) getRequest(IAboutRequest.class);
    }

}
