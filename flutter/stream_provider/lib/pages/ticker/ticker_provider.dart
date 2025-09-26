import 'package:riverpod_annotation/riverpod_annotation.dart';

part 'ticker_provider.g.dart';

// StreamProvider를 사용할 경우 keepAlive가 디폴트
// final tickerProvider = StreamProvider((Ref ref) {
//   ref.onDispose(() {
//     print('[tickerProvider] dispose');
//   });
//   return Stream.periodic(const Duration(seconds: 1), (t) => t + 1);
// });

@riverpod
Stream<int> ticker(Ref ref) {
  ref.onDispose(() {
    print('[tickerProvider] dispose');
  });
  return Stream.periodic(const Duration(seconds: 1), (t) => t + 1);
}
