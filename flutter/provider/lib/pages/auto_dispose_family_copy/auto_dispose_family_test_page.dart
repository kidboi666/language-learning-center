import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'auto_dispose_family_test_provider.dart';

class AutoDisposeFamilyTestPage extends ConsumerStatefulWidget {
  const AutoDisposeFamilyTestPage({super.key});

  @override
  ConsumerState createState() => _AutoDisposeFamilyTestPageState();
}

class _AutoDisposeFamilyTestPageState
    extends ConsumerState<AutoDisposeFamilyTestPage> {
  var name = 'john';

  @override
  Widget build(BuildContext context) {
    final helloThere = ref.watch(autoDisposeFamilyTestHelloProvider(name));

    return Scaffold(
      appBar: AppBar(title: const Text('AutoDisposeFamilyProvider')),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(helloThere),
            FilledButton(
              onPressed: () {
                setState(() {
                  name = name == 'john' ? 'jane' : 'john';
                });
              },
              child: Text('Change name', style: TextStyle(fontSize: 18)),
            ),
          ],
        ),
      ),
    );
  }
}
