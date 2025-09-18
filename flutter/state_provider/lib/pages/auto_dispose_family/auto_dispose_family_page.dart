import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'auto_dispose_family_provider.dart';

class AutoDisposeFamilyPage extends ConsumerWidget {
  const AutoDisposeFamilyPage({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final incVal = ref.watch(autoDisposeFamilyCounterProvider(10));
    final decVal = ref.watch(autoDisposeFamilyCounterProvider(-10));
    return Scaffold(
      appBar: AppBar(title: const Text('FamilyStateProvider')),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text('$incVal'),
                const SizedBox(width: 20),
                FilledButton(
                  onPressed: () {
                    ref
                        .read(autoDisposeFamilyCounterProvider(10).notifier)
                        .update((state) => state + 10);
                  },
                  child: const Text('Increment'),
                ),
              ],
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text('$decVal'),
                const SizedBox(width: 20),
                FilledButton(
                  onPressed: () {
                    ref
                        .read(autoDisposeFamilyCounterProvider(-10).notifier)
                        .update((state) => state - 10);
                  },
                  child: const Text('Decrement'),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
