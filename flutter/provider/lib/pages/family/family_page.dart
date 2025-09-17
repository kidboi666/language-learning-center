import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:provider/pages/family/family_provider.dart';

class FamilyPage extends ConsumerWidget {
  const FamilyPage({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final helloJohn = ref.watch(familyHelloProvider('john'));
    final helloJane = ref.watch(familyHelloProvider('jane'));

    return Scaffold(
      appBar: AppBar(title: const Text('FamilyProvider')),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [Text(helloJohn), Text(helloJane)],
        ),
      ),
    );
  }
}
