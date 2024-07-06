import SwiftUI
import Shared

let appText = SwiftKoin.shared.appText

@main
struct iOSApp: App {
    
    @StateObject private var router: SwiftAppRouter = SwiftAppRouter()

    var body: some Scene {
        WindowGroup {
            SwiftAppView(router: router) {
                router.getRootview()
            }
        }
    }
}
