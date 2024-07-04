//
//  SwiftLoadingState.swift
//  iosApp
//
//  Created by グェン・ホン・ソン on 2024/07/04.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

enum SwiftLoadingState<T> : Equatable, Identifiable where T: Equatable{
    var id: String {
           switch self {
           case .idle:
               return "idle"
           case .loading:
               return "loading"
           case .loaded(_):
               return "loaded_\(UUID().uuidString)"
           case .error(let msg):
               return "error_\(msg)"
           }
       }

    case idle
    case loading
    case loaded(T?)
    case error(String)
    
    var data: T? {
        switch self {
        case let .loaded(data): return data
        default: return nil
        }
    }
    
    static func == (lhs: SwiftLoadingState<T>, rhs: SwiftLoadingState<T>) -> Bool {
           switch (lhs, rhs) {
           case (.idle, .idle),
               (.loading, .loading):
               return true
           case (let .loaded(data1), let .loaded(data2)):
               return data1 == data2
           case (let .error(msg1), let .error(msg2)):
               return msg1 == msg2
           default:
               return false
           }
       }

}

extension LoadingState {
    func asSwiftState<T: AnyObject>() -> SwiftLoadingState<T> {
        switch self {
        case is LoadingStateIdle:
            return .idle
        case is LoadingStateLoading:
            return .loading
        case is LoadingStateLoaded<AnyObject>:
            let loaded = self as! LoadingStateLoaded<T>
            return .loaded(loaded.data)
        case is LoadingStateError:
            let error = self as! LoadingStateError
            return .error(error.mess)
        default:
            return .idle
        }
    }

}
