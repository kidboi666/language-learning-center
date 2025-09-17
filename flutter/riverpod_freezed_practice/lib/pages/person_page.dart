import 'package:flutter/material.dart';
import 'package:riverpod_freezed_practice/models/person.dart';

class PersonPage extends StatelessWidget {
  const PersonPage({super.key});

  @override
  Widget build(BuildContext context) {
    final person1 = Person(id: 1, name: 'Alice', email: 'john@gmail.com');
    print(person1);
    final person2 = Person(id: 1, name: 'Alice', email: 'john@gmail.com');
    print(person1 == person2); // true

    return Scaffold(appBar: AppBar(title: const Text('Person Page')));
  }
}
