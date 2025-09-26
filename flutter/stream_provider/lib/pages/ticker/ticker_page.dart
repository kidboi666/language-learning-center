import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:stream_provider/pages/ticker/ticker_provider.dart';

class TickerPage extends ConsumerWidget {
  const TickerPage({super.key});

  String zeroPaddedTwoDigits(double ticks) {
    return ticks.floor().toString().padLeft(2, '0');
  }

  String formatTimer(int ticks) {
    final minutes = zeroPaddedTwoDigits((ticks / 60) % 60);
    final seconds = zeroPaddedTwoDigits(ticks % 60);
    print(minutes);

    return '$minutes : $seconds';
  }

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final tickerValue = ref.watch(tickerProvider);
    print(tickerValue);
    return Scaffold(
      appBar: AppBar(title: const Text('Ticker')),
      body: Center(
        child: tickerValue.when(
          data: (data) => Text(formatTimer(data)),
          error: (error, stackTrace) => Text(error.toString()),
          loading: () => const CircularProgressIndicator(),
        ),
      ),
    );
  }
}
