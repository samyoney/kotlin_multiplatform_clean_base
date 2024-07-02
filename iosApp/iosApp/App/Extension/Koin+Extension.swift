//
//  AppModule.swift
//  iosApp
//
//  Created by グェン・ホン・ソン on 2024/07/03.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared
import SwiftUI

public typealias KoinApplication = Koin_coreKoinApplication
public typealias Koin = Koin_coreKoin

extension BaseViewModel: ObservableObject { }

extension Array where Element: AnyObject {
    public init(_ kotlin: KotlinArray<Element>) {
        self = (0..<kotlin.size).map { kotlin.get(index: $0)! }
    }
}

public protocol KotlinCaseIterable: AnyObject {
    associatedtype Value: AnyObject

    static func values() -> KotlinArray<Value>
}

public protocol KotlinEnum: KotlinCaseIterable, Identifiable {
    var name: String { get }
}

public func kotlinCaseUnreachable<T: KotlinCaseIterable>(_ value: T) -> Never {
    fatalError("Received unexpected value \(value) when switching over \(type(of: value))")
}

extension KotlinCaseIterable {
    public static var allCases: [Value] {
        Array(values())
    }
}

extension KoinApplication {
    public static let shared = companion.doInitKoin()
    
    @discardableResult
    public static func start() -> KoinApplication {
        shared
    }
}

extension KoinApplication {
    private static let keyPaths: [PartialKeyPath<Koin>] = [
        \.splashViewModel,
        \.loginViewModel,
        \.samViewModel,
         \.appText,
    ]

    public static func inject<T>() -> T {
        shared.inject()
    }

    public func inject<T>() -> T {
        for partialKeyPath in Self.keyPaths {
            guard let keyPath = partialKeyPath as? KeyPath<Koin, T> else { continue }
            return koin[keyPath: keyPath]
        }

        fatalError("\(T.self) is not registered with KoinApplication")
    }
}

@propertyWrapper
public struct LazyKoin<T> {
    public lazy var wrappedValue: T = { KoinApplication.shared.inject() }()

    public init() { }
}
