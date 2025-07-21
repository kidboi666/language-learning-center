import 'package:flutter/material.dart';

// 1. InheritedWidget을 상속받는 카운터 위젯
class CounterInheritedWidget extends InheritedWidget {
  final int counter;
  final VoidCallback increment;
  final VoidCallback decrement;

  const CounterInheritedWidget({
    Key? key,
    required this.counter,
    required this.increment,
    required this.decrement,
    required Widget child,
  }) : super(key: key, child: child);

  // 위젯 트리에서 가장 가까운 CounterInheritedWidget을 찾는 정적 메서드
  static CounterInheritedWidget? of(BuildContext context) {
    return context.dependOnInheritedWidgetOfExactType<CounterInheritedWidget>();
  }

  // 상태가 변경되었는지 확인하는 메서드
  @override
  bool updateShouldNotify(CounterInheritedWidget oldWidget) {
    return counter != oldWidget.counter;
  }
}

// 2. 상태를 관리하는 StatefulWidget
class CounterProvider extends StatefulWidget {
  const CounterProvider({super.key, required this.child});

  final Widget child;

  @override
  _CounterProviderState createState() => _CounterProviderState();
}

class _CounterProviderState extends State<CounterProvider> {
  int _counter = 0;

  void _increment() {
    setState(() {
      _counter++;
    });
  }

  void _decrement() {
    setState(() {
      _counter--;
    });
  }

  @override
  Widget build(BuildContext context) {
    return CounterInheritedWidget(
      counter: _counter,
      increment: _increment,
      decrement: _decrement,
      child: widget.child,
    );
  }
}

// 3. 카운터 값을 표시하는 위젯
class CounterDisplay extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final counterWidget = CounterInheritedWidget.of(context);

    return Text(
      '카운터: ${counterWidget?.counter ?? 0}',
      style: Theme.of(context).textTheme.headlineMedium,
    );
  }
}

// 4. 카운터 버튼들
class CounterButtons extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final counterWidget = CounterInheritedWidget.of(context);

    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      children: [
        FloatingActionButton(
          onPressed: counterWidget?.decrement,
          heroTag: "decrement",
          child: Icon(Icons.remove),
        ),
        FloatingActionButton(
          onPressed: counterWidget?.increment,
          heroTag: "increment",
          child: Icon(Icons.add),
        ),
      ],
    );
  }
}

// 5. 메인 앱
class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'InheritedWidget 카운터',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: CounterProvider(
        child: CounterHomePage(),
      ),
    );
  }
}

// 6. 홈 페이지
class CounterHomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('InheritedWidget 카운터'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(
              '버튼을 눌러 카운터를 변경하세요:',
            ),
            SizedBox(height: 20),
            CounterDisplay(),
            SizedBox(height: 40),
            CounterButtons(),
          ],
        ),
      ),
    );
  }
}

// 7. 메인 함수
void main() {
  runApp(MyApp());
}
