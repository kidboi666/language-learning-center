// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'ticker_provider.dart';

// **************************************************************************
// RiverpodGenerator
// **************************************************************************

// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: type=lint, type=warning

@ProviderFor(ticker)
const tickerProvider = TickerProvider._();

final class TickerProvider
    extends $FunctionalProvider<AsyncValue<int>, int, Stream<int>>
    with $FutureModifier<int>, $StreamProvider<int> {
  const TickerProvider._()
    : super(
        from: null,
        argument: null,
        retry: null,
        name: r'tickerProvider',
        isAutoDispose: true,
        dependencies: null,
        $allTransitiveDependencies: null,
      );

  @override
  String debugGetCreateSourceHash() => _$tickerHash();

  @$internal
  @override
  $StreamProviderElement<int> $createElement($ProviderPointer pointer) =>
      $StreamProviderElement(pointer);

  @override
  Stream<int> create(Ref ref) {
    return ticker(ref);
  }
}

String _$tickerHash() => r'dc21540d8f752c64a133bb44f5f74f4876038b14';
