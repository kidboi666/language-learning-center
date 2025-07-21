import 'package:calendar_scheduler/component/schedule_bottom_sheet.dart';
import 'package:calendar_scheduler/const/colors.dart';
import 'package:flutter/material.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  DateTime selectedDate = DateTime.utc(
    DateTime.now().year,
    DateTime.now().month,
    DateTime.now().day,
  );

  void onDaySelected(DateTime selectedDate, DateTime focusedDate) {
    setState(() {
      this.selectedDate = selectedDate;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      floatingActionButton: FloatingActionButton(
        backgroundColor: PRIMARY_COLOR,
        onPressed: () {
          showModalBottomSheet(
            context: context,
            isDismissible: true,
            builder: (_) => ScheduleBottomSheet(),
          );
        },
        child: Icon(Icons.add),
      ),
      body: SafeArea(
        child: SingleChildScrollView(
          child:
              // MainCalendar(
              //   selectedDate: selectedDate,
              //   onDaySelected: onDaySelected,
              // ),
              // SizedBox(height: 8),
              // TodayBanner(selectedDate: selectedDate, count: 0),
              // ScheduleCard(startTime: 12, endTime: 14, content: '프로그래밍 공부'),
              ListView(
                padding: EdgeInsets.all(8),
                children: [
                  ListTile(
                    title: Text('프로그래밍 공부'),
                    subtitle: Text('12:00 ~ 14:00'),
                    leading: CircleAvatar(child: Icon(Icons.contact_emergency)),
                    trailing: Icon(Icons.more_vert),
                  ),
                ],
              ),
        ),
      ),
    );
  }
}
