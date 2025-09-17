import 'package:flutter/material.dart';
import 'package:riverpod_freezed_practice/pages/person_page.dart';
import 'package:riverpod_freezed_practice/widgets/custom_button.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
      ),
      home: const MyWidget(),
    );
  }
}

class MyWidget extends StatelessWidget {
  const MyWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: ListView(
          padding: EdgeInsets.all(20),
          shrinkWrap: true,
          children: [CustomButton(title: 'Person', child: PersonPage())],
        ),
      ),
    );
  }
}
