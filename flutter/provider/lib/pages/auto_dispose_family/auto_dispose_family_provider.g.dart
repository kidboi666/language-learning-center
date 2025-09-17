// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'auto_dispose_family_provider.dart';

// **************************************************************************
// RiverpodGenerator
// **************************************************************************

// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: type=lint, type=warning

@ProviderFor(counter)
const counterProvider = CounterFamily._();

final class CounterProvider extends $FunctionalProvider<int, int, int>
    with $Provider<int> {
  const CounterProvider._({
    required CounterFamily super.from,
    required Counter super.argument,
  }) : super(
         retry: null,
         name: r'counterProvider',
         isAutoDispose: true,
         dependencies: null,
         $allTransitiveDependencies: null,
       );

  @override
  String debugGetCreateSourceHash() => _$counterHash();

  @override
  String toString() {
    return r'counterProvider'
        ''
        '($argument)';
  }

  @$internal
  @override
  $ProviderElement<int> $createElement($ProviderPointer pointer) =>
      $ProviderElement(pointer);

  @override
  int create(Ref ref) {
    final argument = this.argument as Counter;
    return counter(ref, argument);
  }

  /// {@macro riverpod.override_with_value}
  Override overrideWithValue(int value) {
    return $ProviderOverride(
      origin: this,
      providerOverride: $SyncValueProvider<int>(value),
    );
  }

  @override
  bool operator ==(Object other) {
    return other is CounterProvider && other.argument == argument;
  }

  @override
  int get hashCode {
    return argument.hashCode;
  }
}

String _$counterHash() => r'08773761487cb4e0597183ae0c69062669858f43';

final class CounterFamily extends $Family
    with $FunctionalFamilyOverride<int, Counter> {
  const CounterFamily._()
    : super(
        retry: null,
        name: r'counterProvider',
        dependencies: null,
        $allTransitiveDependencies: null,
        isAutoDispose: true,
      );

  CounterProvider call(Counter c) => CounterProvider._(argument: c, from: this);

  @override
  String toString() => r'counterProvider';
}

@ProviderFor(autoDisposeFamilyHello)
const autoDisposeFamilyHelloProvider = AutoDisposeFamilyHelloFamily._();

final class AutoDisposeFamilyHelloProvider
    extends $FunctionalProvider<String, String, String>
    with $Provider<String> {
  const AutoDisposeFamilyHelloProvider._({
    required AutoDisposeFamilyHelloFamily super.from,
    required String super.argument,
  }) : super(
         retry: null,
         name: r'autoDisposeFamilyHelloProvider',
         isAutoDispose: true,
         dependencies: null,
         $allTransitiveDependencies: null,
       );

  @override
  String debugGetCreateSourceHash() => _$autoDisposeFamilyHelloHash();

  @override
  String toString() {
    return r'autoDisposeFamilyHelloProvider'
        ''
        '($argument)';
  }

  @$internal
  @override
  $ProviderElement<String> $createElement($ProviderPointer pointer) =>
      $ProviderElement(pointer);

  @override
  String create(Ref ref) {
    final argument = this.argument as String;
    return autoDisposeFamilyHello(ref, argument);
  }

  /// {@macro riverpod.override_with_value}
  Override overrideWithValue(String value) {
    return $ProviderOverride(
      origin: this,
      providerOverride: $SyncValueProvider<String>(value),
    );
  }

  @override
  bool operator ==(Object other) {
    return other is AutoDisposeFamilyHelloProvider &&
        other.argument == argument;
  }

  @override
  int get hashCode {
    return argument.hashCode;
  }
}

String _$autoDisposeFamilyHelloHash() =>
    r'661eb4b0c92026a942bb79e8f5a955bda92b917b';

final class AutoDisposeFamilyHelloFamily extends $Family
    with $FunctionalFamilyOverride<String, String> {
  const AutoDisposeFamilyHelloFamily._()
    : super(
        retry: null,
        name: r'autoDisposeFamilyHelloProvider',
        dependencies: null,
        $allTransitiveDependencies: null,
        isAutoDispose: true,
      );

  AutoDisposeFamilyHelloProvider call(String a) =>
      AutoDisposeFamilyHelloProvider._(argument: a, from: this);

  @override
  String toString() => r'autoDisposeFamilyHelloProvider';
}
