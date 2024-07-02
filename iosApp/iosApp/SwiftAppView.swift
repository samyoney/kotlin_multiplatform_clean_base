import SwiftUI
import Shared

struct SwiftAppView<Content: View>: View {
    @StateObject private var router: SwiftAppRouter
    private let content: Content
    
    init(router: SwiftAppRouter, @ViewBuilder content: @escaping () -> Content) {
        _router = StateObject(wrappedValue: router)
        self.content = content()
    }
    
    var body: some View {
        NavigationStack(path: $router.path) {
            content
                .navigationDestination(for: SwiftAppRouter.Route.self) { route in
                    router.view(for: route)
                }
        }
        .sheet(item: $router.presentingSheet) { route in
            router.view(for: route)
        }
        .fullScreenCover(item: $router.presentingFullScreenCover) { route in
            router.view(for: route)
        }.environmentObject(router)
    }}



