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


@propertyWrapper
public struct KoinInject<T> {
    public lazy var wrappedValue: T = {
        return SwiftKoin.shared.inject()
    }()

    public init() { }
}

extension SwiftKoin {
    func inject<T>() -> T{
        guard let result = SwiftKoin.shared.get(objCClass: T.self as! AnyClass) as? T else {
             fatalError("Koin can't provide an instance of type: \(T.self)")
           }
        return result
    }
}
