package com.example.activeMQ.rtle;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import com.jinglinsoft.rtle.api.Anchor;
import com.jinglinsoft.rtle.api.Anchors;
import com.jinglinsoft.rtle.api.Area;
import com.jinglinsoft.rtle.api.Areas;
import com.jinglinsoft.rtle.api.ClockSource;
import com.jinglinsoft.rtle.api.ClockSources;
import com.jinglinsoft.rtle.api.RTLEAPI;
import com.jinglinsoft.rtle.api.Tag;
import com.jinglinsoft.rtle.api.Tags;
import com.jinglinsoft.rtle.api.event.RTLEEvent_TagMessage;
import com.jinglinsoft.rtle.api.event.RTLSEvent_TagLocated;

public class PrintTagCoordinate {

	public static void main(String[] args) throws IOException, InterruptedException {
		RTLEAPI.start("192.168.9.96");	// 启动接口, 当接口启动后, 接口会自动与定位引擎同步数据

		// API与引擎同步数据需要时间, 我们不知道要等多久
		// 标签是最后同步的, 如果标签数量已经不是0，说明区域、基站等已经同步完成
		while (Tags.getTagList().isEmpty()) {
			Thread.sleep(1000);		// 如果标签数量为0，则休眠一会
			System.out.println("等待 API 与引擎同步数据...");
		}
		Thread.sleep(2000);		// 再等2秒，确保标签数据已经同步完成


		System.out.println("以下是区域列表");
		System.out.println("-----------------------------------------------");
		for (Area area : Areas.getAreaList().values()) {
			System.out.println(String.format("areaId=%s areaName=%s", area.getId(), area.getName()));
		}
		System.out.println("");

		System.out.println("以下是基站列表");
		System.out.println("-----------------------------------------------");
		for (Anchor anchor : Anchors.getAnchorList().values()) {
			System.out.println(String.format("anchorId=%s anchorName=%s", anchor.getId(), anchor.getName()));
		}
		System.out.println("");

		System.out.println("以下是时钟源列表");
		System.out.println("-----------------------------------------------");
		for (ClockSource cs : ClockSources.getClockSourceList().values()) {
			System.out.println(String.format("csId=%s csName=%s", cs.getId(), cs.getName()));
		}
		System.out.println("");


		System.out.println("以下是标签列表");
		System.out.println("-----------------------------------------------");
		for (Tag tag : Tags.getTagList().values()) {
			System.out.println(String.format("tagId=%s tagName=%s", tag.getId(), tag.getName()));
		}
		System.out.println("-----------------------------------------------");


		// 添加观察者, 侦听定位相关事件
		EventWatcher ew = new EventWatcher();
		RTLEAPI.getRTLSEventMonitor().addObserver(ew);	// 添加观察者

		// 以下循环等待，按 q 键退出程序
		boolean wait = true;
		while (wait) {
			int c = System.in.read();
			if ('q' == (char)c)
				wait = false;

			Thread.sleep(100);
		}
		RTLEAPI.getRTLSEventMonitor().deleteObserver(ew);	// 删除观察者

		RTLEAPI.stop();	// 停止接口
	}


}

// 这个类是观察者的实现, 用于侦听定位相关事件
class EventWatcher implements Observer {
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof RTLEEvent_TagMessage){
			RTLEEvent_TagMessage event = (RTLEEvent_TagMessage)arg;
			System.out.print("Event RTLEEvent_TagMessage: ");
			System.out.println(String.format("\ttagId=%s seq=%d Battery=%.2fV",
					event.tagMessage.getTagId(), event.tagMessage.getSeq64(), event.tagMessage.getBatteryVoltage()));
		}
		else if(arg instanceof RTLSEvent_TagLocated){
			RTLSEvent_TagLocated event = (RTLSEvent_TagLocated)arg;
			System.out.print("Event RTLSEvent_TagLocated: ");

			System.out.println(String.format("\ttagId=%s seq=%d @(%.2f, %.2f, %.2f) Battery=%.2fV Switch=%d",
					event.tag.getId(), event.tag.getSeq64(),
					event.tag.getX(), event.tag.getY(), event.tag.getZ(),
					event.tag.getBatteryVoltage(), event.tag.getSwitchStatus()));
		}
		else {
			System.out.println("Event class name: "+arg.getClass().getName());
		}
	}
}

