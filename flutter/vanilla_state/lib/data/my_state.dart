import 'package:flutter/material.dart';

class MyState extends InheritedWidget {
  const MyState({super.key, required this.counter, required super.child});

  final String counter;


  static MyState of(BuildContext context) {
    final result = context
        .dependOnInheritedWidgetOfExactType<MyState>();
    assert(result != null, 'No MyState found in context');
    return result!;
  }

  @override
  bool updateShouldNotify(MyState old) => counter != old.counter;
}
